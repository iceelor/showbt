/*
 * ------------------------------------------
 * 模块调度器实现文件
 * @version  1.0
 * @author   genify(caijf@corp.netease.com)
 * ------------------------------------------
 */
var f = function(){
    // variable declaration
    var _  = NEJ.P,
        _o = NEJ.O,
        _f = NEJ.F,
        _e = _('nej.e'),
        _v = _('nej.v'),
        _u = _('nej.u'),
        _p = _('nej.ut'),
        _d = _('nej.ut.p'),
        _proDispatcher;
    if (!!_d._$$Dispatcher) return;
    /**
     * 调度器对象，项目仅允许实例化一个调度器
     * [code]
     *   // 取调度器实例
     *   var dispatcher = nej.ut._$$Dispatcher._$getInstance();
     *   
     *   // 添加规则
     *   dispatcher._$rule({
     *       title:{
     *           '/m/a':'模块标题',
     *           '/m/b':'模块标题'
     *       },
     *       rewrite:[
     *          {'/m/a':''},
     *          {'/m/b':/^\/m\/d.*$/i}
     *       ]
     *   });
     *   
     *   // 注册模块
     *   dispatcher._$regist({
     *       '/m/a':'/m/a.html',
     *       '/m/b':'/m/b.html',
     *       '/m/c':'/m/c.html'
     *   });
     *   
     *   // 激活调度器
     *   dispatcher._$active();
     *   
     *   // 以上逻辑可以采用链式编码风格
     *   var dispatcher = nej.ut._$$Dispatcher._$getInstance()
     *       ._$rule()
     *       ._$regist({
     *           '/m/a':'/m/a.html',
     *           '/m/b':'/m/b.html',
     *           '/m/c':'/m/c.html'
     *       })
     *       ._$active();
     *   
     *   // 以上逻辑也可通过构造参数方式输入
     *   var dispatcher = nej.ut._$$Dispatcher._$getInstance({
     *       rules:{
     *           title:{
     *               '/m/a':'模块标题',
     *               '/m/b':'模块标题'
     *           },
     *           rewrite:[
     *              {'/m/a':''},
     *              {'/m/b':/^\/m\/b.*$/i}
     *           ]
     *       },
     *       modules:{
     *           '/m/a':'/m/a.html',
     *           '/m/b':'/m/b.html',
     *           '/m/c':'/m/c.html'
     *       },
     *       onbeforechange:function(_event){
     *           // 根据dispatcher规则解析出来的路径信息
     *           // _event.path   <--- 模块UMI
     *           // _event.href   <--- 路径完整信息
     *           // _event.query  <--- 查询参数信息
     *           // 可以通过修改以上参数调整调度模块
     *       }
     *   })._$active();
     *   
     *   // 调度器激活前需确保当前可能出现的模块均已注册到调度器中
     *   // 实际应用中常出现以下情况
     *   var dispatcher = nej.ut._$$Dispatcher._$getInstance({
     *       modules:{
     *           '/m/a':'/m/a.html',
     *           '/m/b':'/m/b.html',
     *           '/m/c':'/m/c.html'
     *       }
     *   });
     *   // 先解析页面模板，因为在解析模板的过程中可能会有模块构造器的注册逻辑
     *   // 注册模块构造器的优先级要比注册模板文件地址的优先级高，
     *   // 因此必须确保此处可能出现注册构造器的逻辑已处理以避免调度器激活时发出模块模板文件的请求
     *   // 比如 在模板内联了'/m/c'模块的相关模板（包括样式、结构和逻辑），如果此处不先解析模板，
     *   // 则在调度器激活时，调度器会识别出'/m/c'对应的是模板文件'/m/c.html'，因此回去加载这个文件，
     *   // 而实际上这个文件的内容已内联在页面的模板中，因此会产生额外的请求开销
     *   nej.e._$parseTemplate('template-box');
     *   // 激活调度器
     *   dispatcher._$active();
     * [/code]
     * 
     * @singleton
     * @class    {nej.ut._$$Dispatcher}
     * @extends  {nej.ut._$$Event}
     * @uses     {nej.ut._$$Module}
     * @uses     {nej.ut.p._$$Node}
     * @uses     {nej.ut.p._$$GroupManager}
     * @uses     {nej.ut.p._$$SingleGroupManager}
     * 
     * @param    {Object} 可选配置参数，已处理的参数列表如下
     * @config   {Object} modules 模块配置
     * @config   {Object} rules   规则配置
     * 
     * [hr]
     * 地址变换之前触发事件
     * @event    {onbeforechange}
     * @param    {Object} 地址信息
     * 
     */
    _p._$$Dispatcher = NEJ.C();
      _proDispatcher = _p._$$Dispatcher._$extend(_p._$$Event)
    /**
     * 控件初始化
     * @protected
     * @method {__init}
     * @return {Void}
     */
    _proDispatcher.__init = function(){
        this.__supInit();
        var _seed = +new Date;
        this.__pbseed = 'pb-'+_seed;
        this.__pvseed = 'pv-'+_seed;
    };
    /**
     * 控件重置
     * @protected
     * @method {__reset}
     * @param  {Object} 可选配置参数
     * @return {Void}
     */
    _proDispatcher.__reset = function(_options){
        this.__root = _d._$$Node._$allocate();
        // config map
        // - m   config for module, umi:{title:'xxx' ... }
        // - mg  umi to group id map, umi:gid
        // - r   config for rewrite, [{umi:regexp or string}]
        // - rr  build-in rewrite
        this.__config = {m:{},mg:{},r:[],rr:{}};
        this.__groups = {};
        // for public module umi manager
        this.__doBuildGroup(this.__pbseed);
        // for private module umi manager
        this.__groups[this.__pvseed] = 
             _d._$$GroupManager._$allocate({
                 root:this.__root
             });
        // listen urlchange
        this.__doInitDomEvent([
            [location,'urlchange',this.__onURLChange._$bind(this)]
        ]);
        this.__supReset(_options);
        // init config
        this._$rule(_options.rules);
        this._$regist(_options.modules);
    };
    /**
     * 控件销毁
     * @protected
     * @method {__destroy}
     * @return {Void}
     */
    _proDispatcher.__destroy = (function(){
        var _doRecycle = function(_group,_key,_map){
            delete _map[_key];
            _group._$recycle();
        };
        return function(){
            delete this.__config;
            this.__root = this.__root._$recycle();
            _u._$forIn(this.__groups,_doRecycle);
            this.__supDestroy();
        };
    })();
    /**
     * 设置模块配置信息
     * @protected
     * @method {__setModuleConf}
     * @param  {String}   模块UMI
     * @param  {String}   配置类别
     * @param  {Variable} 配置信息
     * @return {Void}
     */
    _proDispatcher.__setModuleConf = function(_umi,_key,_value){
        var _mconf = this.__config.m[_umi];
        if (!_mconf){
            _mconf = {};
            this.__config.m[_umi] = _mconf;
        }
        _mconf[_key] = _value;
    };
    /**
     * 取模块配置信息
     * @param  {String} 模块UMI
     * @param  {String} 配置标识
     * @return {String} 配置信息
     */
    _proDispatcher.__getModuleConf = function(_umi,_key){
    	var _mconf = this.__config.m[_umi];
    	return !_mconf?'':_mconf[_key];
    };
    /**
     * 构建分组管理器
     * @protected
     * @method {__doBuildGroup}
     * @param  {String} 管理器标识
     * @return {nej.ut.p._$$SingleGroupManager}
     *                  分组管理器实例
     */
    _proDispatcher.__doBuildGroup = function(_gid){
        if (!_gid) return;
        var _group = this.__groups[_gid];
        if (!_group){
            _group = _d._$$SingleGroupManager._$allocate({
                         root:this.__root,
                         classed:_gid==this.__pbseed
                     });
            this.__groups[_gid] = _group;
        }
        return _group;
    };
    /**
     * 添加UMI至分组管理器
     * @protected
     * @method {__doAddUMI2Group}
     * @param  {String} 模块UMI
     * @param  {String} 分组标识
     * @return {Void}
     */
    _proDispatcher.__doAddUMI2Group = function(_umi,_gid){
        var _group = this.__doBuildGroup(_gid);
        if (!_group){
            _gid = _d._$isUMIPrivate(_umi)
                 ? this.__pvseed : this.__pbseed;
            _group = this.__groups[_gid];
        }
        _group._$addUMI(_umi);
        this.__config.mg[_umi] = _gid;
    };
    /**
     * 重写UMI规则
     * @protected
     * @method {__doRewriteUMI}
     * @param  {String} 模块UMI
     * @return {String} 重写后模块UMI
     */
    _proDispatcher.__doRewriteUMI = (function(){
        var _reg = /\$\d/;
        return function(_umi){
            var _result;
            _u._$forIn(this.__config.r,
                function(_config){
                    _u._$forIn(_config,
                        function(_value,_key){
                            if (!!_value.test
                                &&_value.test(_umi)){
                                // /^\/a\/([\d]+)\/([\d]+)\/$/ ---> /a/?p=$1&k=$2
                                // /a/123/456/ ---> /a/?p=123&k=456
                                _result = _reg.test(_key)?_umi.replace(_value,_key):_key
                                return !0;
                            }
                            return _value===_umi;
                        });
                    return !!_result;
                });
            return _result||_umi;
        };
    })();
    /**
     * URL变化触发事件
     * @protected
     * @method {__onURLChange}
     * @param  {Object} 地址信息
     * @return {Void}
     */
    _proDispatcher.__onURLChange = (function(){
        var _trim = /(?:^\/+)|(?:\/+$)/gi;
        var _doParseRestParam = function(_umi,_node){
            var _path = _node._$getPath(),
                _umi = _umi.replace(_path,'')
                           .replace(_trim,'');
            return _umi.split('/');
        };
        return function(_location){
            this._$dispatchEvent('onbeforechange',_location);
            var _umi = this.__doRewriteUMI(_location.path),
                _gid = this.__config.mg[_umi];
            // public umi not registed
            if (!_gid&&!_d._$isUMIPrivate(_umi)){
                _umi = this.__config.rr['404'];
                _gid = this.__config.mg[_umi];
            }
            if (!_gid) return;
            // save dispatch event
            var _node = _d._$getNodeByUMI(this.__root,_umi);
            _node._$getData().event = {
                target:_umi,
                href:_location.href,
                param:_location.query,
                prest:_doParseRestParam(_umi,_node),
                clazz:this.__getModuleConf(_umi,'clazz')
            };
            // dispatch module
            this.__groups[_gid]._$dispatchUMI(_umi);
        };
    })();
    /**
     * 点击代理
     * @protected
     * @method {__onClickDelegate}
     * @param  {Event} 点击事件对象
     * @return {Void}
     */
    _proDispatcher.__onClickDelegate = (function(){
        // check event need delegated
        var _isNode = function(_node){
            return !!_e._$dataset(_node,'href');
        };
        return function(_event){
            var _element = _v._$getElement(_event,_isNode);
            if (!!_element){
                _v._$stopDefault(_event);
                this._$redirect(_e._$dataset(_element,'href'));
            }
        };
    })();
    /**
     * 添加调度规则
     * [code]
     *   // 配置模块标题
     *   dispatcher._$rule('title',{
     *       '/m/a':'模块标题',
     *       '/m/b':'模块标题',
     *       '/m/c':'模块标题'
     *   });
     *   
     *   // 配置与匹配顺序无关重写规则
     *   // 重写规则配置结构：{ 目标UMI:重写规则 }
     *   // 重写规则可以是字符串（全字符匹配）或者正则表达式
     *   dispatcher._$rule('rewrite',{
     *       '/m/b':/^\/m\/b.*$/i,
     *       '/m/c':'/m/d'
     *   });
     *   
     *   // 批量配置重写规则
     *   // 重写规则内置匹配代码支持
     *   // 404 - 当模块不存在时重定向的模块UMI
     *   dispatcher._$rule([
     *       {'/m/a':'/m/','/m/c':'/m/d'},  // <---- 此处两条规则匹配与顺序无关
     *       {'/m/b':/^\/m\/b.*$/i},
     *       {'404':'/m/a'}                 // <---- 模块不存在时定向到/m/a模块
     *   ]);
     * [/code]
     * 
     * @method {_$rule}
     * @param  {String}       规则类型，支持类型: title/rewrite
     * @param  {Object|Array} 规则配置，对于重写规则存在匹配的先后顺序
     * @return {nej.ut._$$Dispatcher}
     */
    _proDispatcher._$rule = (function(){
        var _buildin = ['404'];
        // regist rule
        var _doRegistRule = function(_config,_key){
            this._$rule(_key,_config);
        };
        // regist title
        var _doRegistTitle = function(_title,_umi){
            this.__setModuleConf(_umi,'title',_title);
        };
        // regist rewrite
        var _doRegistRewrite = function(_config){
            if (!_config) return;
            this.__config.r.push(_config);
            // parse build-in rewrite rule
            _u._$forEach(_buildin,
                function(_key){
                    if (!_config[_key]) return;
                    this.__config.rr[_key] = _config[_key];
                    delete _config[_key];
                },this);
        };
        // rule parse function map
        var _fmap = {
            title:function(_config){
                _u._$forIn(_config,
                   _doRegistTitle,this);
            },
            rewrite:function(_config){
                if (!_u._$isArray(_config)){
                    _doRegistRewrite.call(this,_config);
                }else{
                    _u._$forEach(_config,
                       _doRegistRewrite,this);
                }
            }
        };
        return function(_key,_config){
            // batch regist rule
            if (!_u._$isString(_key)){
                _u._$forIn(_key,
                   _doRegistRule,this);
                return this;
            }
            // regist rule by type
            (_fmap[_key]||_f).call(this,_config);
        };
    })();
    /**
     * 注册UMI与模块的对应关系
     * [code]
     *   // 注册模块的模板文件路径
     *   dispatcher._$regist('/m/a/','/m/a.html');
     *   
     *   // 注册模块的配置信息，包括标题和文件路径
     *   dispatcher._$regist('/m/a/',{
     *       title:'模块标题',
     *       clazz:'g-ma',
     *       module:'/m/a.html'
     *   });
     *   
     *   // 注册模块的配置信息，包括标题和模块构造器
     *   dispatcher._$regist('/m/a/',{
     *       title:'模块标题',
     *       clazz:'g-ma',
     *       module:np.m._$$ModuleA
     *   });
     *   
     *   // 注册模块的构造器
     *   dispatcher._$regist('/m/a/',np.m._$$ModuleA);
     *   
     *   // 注册私有模块指定分组ID，同一分组的私有模块调度时仅显示一个模块
     *   dispatcher._$regist('/?/a/b/',{
     *       gid:'234567890',
     *       module:'/m/a/b.html'
     *   });
     *   
     *   // 批量注册模块
     *   dispatcher._$regist({
     *       '/m/a/':'/m/a.html',
     *       '/m/a/a':np.m._$$ModuleAA,
     *       '/m/b/':{
     *                 title:'模块标题',
     *                 clazz:'g-mb',
     *                 module:'/m/b.html'
     *               },
     *       '/m/c/':{
     *                 title:'模块标题',
     *                 clazz:'g-mc',
     *                 module:np.m._$$ModuleC
     *               },
     *       '/?/m/a/':'/m/s/a.html',
     *       '/?/m/a/a':np.m.s._$$ModuleAA,
     *       '/?/m/b/':{
     *                   gid:'abc',
     *                   title:'模块标题',
     *                   module:'/m/b.html'
     *                 },
     *       '/?/m/c/':{
     *                   gid:'abc',
     *                   title:'模块标题',
     *                   module:np.m._$$ModuleC
     *                 },
     *   });
     * [/code]
     * 
     * @method {_$regist}
     * @param  {String|Object}   统一模块标识或者模块批量添加信息
     * @param  {String|Object|nej.ut._$$Module} 
     *                           模块构造或者模块路径或者模块配置信息
     * @config {String}   gid    指定模块分组，仅对私有模块有效，对外模块忽略此配置
     * @config {String}   title  模块标题，显示模块时修改页面的标题
     * @config {String}   clazz  模块切换时body样式调整，仅对公共模块有效
     * @config {String|nej.ut._$$Module}
     *                    module 指定模块对应的模板文件地址或者模块的构造函数
     * @return {nej.ut._$$Dispatcher} 调度器实例
     */
    _proDispatcher._$regist = (function(){
        // regist single module
        var _doRegistUMI = function(_config,_umi){
            this._$regist(_umi,_config);
        };
        // check if module constrctor
        var _isModuleClass = function(_module){
            return !!_module&&!!_module._$extend;
        };
        // function body
        return function(_umi,_config){
            // batch regist
            if (!_u._$isString(_umi)){
                _u._$forIn(_umi,
                   _doRegistUMI,this);
                return this;
            }
            // regist module with umi
            var _data = _d._$appendNodeByUMI(this
                          .__root,_umi)._$getData(),
                _module = _data.module;
            // ignore if 
            // - module constructor is registed 
            // - module is instanced
            if (_d._$isModule(_module)||
                _isModuleClass(_module))
                return this;
            // parse conifg
            var _gid = this.__config.mg[_umi],_module;
            if (_u._$isString(_config)||
                _isModuleClass(_config)){
                // config is module template file or module constructor
                _module = _config;
            }else{
                // conifg is module information
                _config = _config||_o;
                _gid = _config.gid;
                _module = _config.module;
                // cache module title
                if (!!_config.title)
                    this.__setModuleConf(
                          _umi,'title',_config.title);
                // cache module clazz
                if (!!_config.clazz)
                    this.__setModuleConf(
                          _umi,'clazz',_config.clazz);
            }
            // save module
            this.__doAddUMI2Group(_umi,_gid);
            _data.module = _module;
            return this;
        };
    })();
    /**
     * 发送消息
     * @method {_$message}
     * @param  {Object} 消息相关信息
     * @config {String} to   消息目标UMI
     * @config {String} from 消息来源UMI
     * @config {Object} data 消息数据
     * @config {Number} mode 消息模式
     *                       0 - 目标消息【默认】，只有目标节点收到消息
     *                       1 - 目标广播，从根节点至路目标节点径上的节点收到消息
     *                       2 - 群体广播，节点下所有子孙子孙节点收到消息
     * @return {nej.ut._$$Dispatcher} 调度器实例
     */
    _proDispatcher._$message = (function(){
        // send message
        var _doSendMessage = function(_node,_message){
            var _module = _node._$getData().module;
            if (_d._$isModule(_module))
                _module._$dispatchEvent('onmessage',_message);
        };
        // message mode function
        var _fmap = [
            _doSendMessage,
            // send message to every node in target path
            function(_target,_message){
                var _from = _message.from;
                while(!!_target){
                    if (_target._$getPath()!=_from)
                        _doSendMessage(_target,_message);
                    _target = _target._$getParent();
                }
            },
            // broadcast to all target descendants
            function(_target,_message){
                var _from = _message.from;
                _d._$breadthFirstSearch(_target,
                    function(_node){
                        if (_node._$getPath()!=_from)
                            _doSendMessage(_node,_message);
                    });
            }
        ];
        return function(_message){
            var _event = NEJ.X({},_message),
                _target = _d._$getNodeByUMI(this.__root,_event.to);
            _event.path = _target._$getPath();
           (_fmap[_event.mode]||_fmap[0]).call(this,_target,_event);
        };
    })();
    /**
     * 应用私有模块
     * @method {_$apply}
     * @see    {#_$redirect}
     * @param  {String} 私有模块地址
     * @return {nej.ut._$$Dispatcher} 调度器实例
     */
    _proDispatcher._$apply = function(_url){
        return this._$redirect(_url);
    };
    /**
     * 重定向模块，此接口支持
     * <ul>
     *   <li>私有模块重定向</li>
     *   <li>可访问模块退出前验证</li>
     *   <li>重定向地址带查询参数</li>
     * </ul>
     * [code]
     *   // 重定向私有模块
     *   dispatcher._$redirect('/?/m/a/b?a=aa&b=bb');
     *   
     *   // 系统存在需退出验证模块时需使用以下业务逻辑接管页面自动调整的逻辑
     *   var _v = NEJ.P('nej.v'),
     *       _e = NEJ.P('nej.e');
     *   _v._$addEvent(document,'click',function(_event){
     *       var _element = _v._$getElement(_event,
     *                      function(_node){
     *                          // filter node here
     *                          // e.g. _node.tagName=='A'
     *                      });
     *       if (!!_element){
     *           _v._$stopDefault(_event);
     *           dispatcher._$redirect(_e._$dataset(_element,'href'));
     *       }
     *   });
     *   
     *   // 如果页面带跳转的节点有data-href标识跳转地址的情况可以使用{#_$delegate}处理以上业务逻辑
     *   // <span data-href="#/m/a/?a=aa">aaaa</span>
     *   // <a href="#/m/a/?a=aa" data-href="#/m/a/?a=aa">bbbbbb</a>
     *   dispatcher._$delegate();
     * [/code]
     * 
     * @method {_$redirect}
     * @param  {String}  模块UMI，可以带查询参数
     * @param  {Boolean} 是否替换当前历史
     * @return {nej.ut._$$Dispatcher} 调度器实例
     */
    _proDispatcher._$redirect = function(_url,_replaced,_force){
        var _umi = _d._$path2umi(_url),
            _location = location.parse(_url);
        if (_d._$isUMIPrivate(_umi)){
            // dispatch private module
            this.__onURLChange(_location);
        }else{
            // dispatch public module
            var _group = this.__groups[
                         this.__pbseed],
                _event = {target:_location,umi:_umi};
            if (!!_group._$exitable(_event)){
                if (location.same(_url)&&!!_force){
                    this.__onURLChange(_location);
                }else{
                    location.redirect(_url,_replaced);
                }
            }
        }
        return this;
    };
    /**
     * 刷新模块
     * @method {_$refresh}
     * @param  {String} 模块UMI，可以带查询参数
     * @return {nej.ut._$$Dispatcher} 调度器实例
     */
    _proDispatcher._$refresh = function(_url){
        if (!!_url){
            this._$redirect(_url,!0,!0);
        }else{
            this.__groups[this.__pbseed]._$refresh();
        }
    };
    /**
     * 模块切换跳转委托，
     * 如果系统存在需退出验证模块时需使用此接口接管页面自动调整的逻辑
     * @method {_$delegate}
     * @see    {#_$redirect}
     * @return {nej.ut._$$Dispatcher} 调度器实例
     */
    _proDispatcher._$delegate = function(){
        this.__doInitDomEvent([
            [document,'click',this.__onClickDelegate._$bind(this)]
        ]);
        // TODO 
        return this;
    };
    /**
     * 激活调度器，激活之前确保注册完会被调用的模块
     * @method {_$active}
     * @see    {location#active}
     * @return {nej.ut._$$Dispatcher} 调度器实例
     */
    _proDispatcher._$active = function(){
        location.active();
        return this;
    };
    /**
     * 模块载入回调
     * @method {_$loaded}
     * @param  {String}           模块UMI
     * @param  {nej.ut._$$Module} 模块构造器
     * @return {nej.ut._$$Dispatcher}
     */
    _proDispatcher._$loaded = function(_umi,_module){
        this._$regist(_umi,_module);
        this.__groups[this.__config.mg[_umi]]._$loadedUMI(_umi);
        return this;
    };
};
define('{lib}util/dispatcher/dispatcher.2.js',
      ['{lib}util/dispatcher/dsp/group.single.js'
      ,'{lib}util/history/history.js'],f);
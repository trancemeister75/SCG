/* automatically generated by JSCoverage - do not edit */
try {
  if (typeof top === 'object' && top !== null && typeof top.opener === 'object' && top.opener !== null) {
    // this is a browser window that was opened from another window

    if (! top.opener._$jscoverage) {
      top.opener._$jscoverage = {};
    }
  }
}
catch (e) {}

try {
  if (typeof top === 'object' && top !== null) {
    // this is a browser window

    try {
      if (typeof top.opener === 'object' && top.opener !== null && top.opener._$jscoverage) {
        top._$jscoverage = top.opener._$jscoverage;
      }
    }
    catch (e) {}

    if (! top._$jscoverage) {
      top._$jscoverage = {};
    }
  }
}
catch (e) {}

try {
  if (typeof top === 'object' && top !== null && top._$jscoverage) {
    _$jscoverage = top._$jscoverage;
  }
}
catch (e) {}
if (typeof _$jscoverage !== 'object') {
  _$jscoverage = {};
}
if (! _$jscoverage['dojo/pentaho/common/button.js']) {
  _$jscoverage['dojo/pentaho/common/button.js'] = [];
  _$jscoverage['dojo/pentaho/common/button.js'][1] = 0;
  _$jscoverage['dojo/pentaho/common/button.js'][5] = 0;
  _$jscoverage['dojo/pentaho/common/button.js'][12] = 0;
  _$jscoverage['dojo/pentaho/common/button.js'][20] = 0;
  _$jscoverage['dojo/pentaho/common/button.js'][24] = 0;
  _$jscoverage['dojo/pentaho/common/button.js'][25] = 0;
}
_$jscoverage['dojo/pentaho/common/button.js'].source = ["dojo<span class=\"k\">.</span>provide<span class=\"k\">(</span><span class=\"s\">'pentaho.common.button'</span><span class=\"k\">);</span>","","<span class=\"c\">// TODO support disabled mode and rollover effects</span>","","dojo<span class=\"k\">.</span>declare<span class=\"k\">(</span>","    <span class=\"s\">'pentaho.common.button'</span><span class=\"k\">,</span>","    <span class=\"k\">[</span>dijit<span class=\"k\">.</span>_Widget<span class=\"k\">,</span> dijit<span class=\"k\">.</span>_Templated<span class=\"k\">],</span>","    <span class=\"k\">{</span>","        label <span class=\"k\">:</span> <span class=\"s\">'a button'</span><span class=\"k\">,</span>","          ","        onClick<span class=\"k\">:</span> <span class=\"k\">function</span><span class=\"k\">()</span> <span class=\"k\">{</span>","            <span class=\"k\">this</span><span class=\"k\">.</span>callback<span class=\"k\">();</span>","        <span class=\"k\">}</span><span class=\"k\">,</span>","        ","        callback<span class=\"k\">:</span> <span class=\"k\">null</span><span class=\"k\">,</span>","","        templatePath<span class=\"k\">:</span> dojo<span class=\"k\">.</span>moduleUrl<span class=\"k\">(</span><span class=\"s\">'pentahocommon'</span><span class=\"k\">,</span> <span class=\"s\">'button.html'</span><span class=\"k\">),</span>","        ","        postMixInProperties<span class=\"k\">:</span> <span class=\"k\">function</span><span class=\"k\">()</span> <span class=\"k\">{</span>","            <span class=\"k\">this</span><span class=\"k\">.</span>inherited<span class=\"k\">(</span>arguments<span class=\"k\">);</span>","        <span class=\"k\">}</span><span class=\"k\">,</span>","        ","        postCreate<span class=\"k\">:</span> <span class=\"k\">function</span><span class=\"k\">()</span> <span class=\"k\">{</span>","            <span class=\"k\">this</span><span class=\"k\">.</span>inherited<span class=\"k\">(</span>arguments<span class=\"k\">);</span>","            dojo<span class=\"k\">.</span>connect<span class=\"k\">(</span><span class=\"k\">this</span><span class=\"k\">.</span>button<span class=\"k\">,</span> <span class=\"s\">\"onclick\"</span><span class=\"k\">,</span> <span class=\"k\">this</span><span class=\"k\">,</span> <span class=\"k\">this</span><span class=\"k\">.</span>onClick<span class=\"k\">);</span>","        <span class=\"k\">}</span>","      <span class=\"k\">}</span>","<span class=\"k\">);</span>"];
_$jscoverage['dojo/pentaho/common/button.js'][1]++;
dojo.provide("pentaho.common.button");
_$jscoverage['dojo/pentaho/common/button.js'][5]++;
dojo.declare("pentaho.common.button", [dijit._Widget, dijit._Templated], {label: "a button", onClick: (function () {
  _$jscoverage['dojo/pentaho/common/button.js'][12]++;
  this.callback();
}), callback: null, templatePath: dojo.moduleUrl("pentahocommon", "button.html"), postMixInProperties: (function () {
  _$jscoverage['dojo/pentaho/common/button.js'][20]++;
  this.inherited(arguments);
}), postCreate: (function () {
  _$jscoverage['dojo/pentaho/common/button.js'][24]++;
  this.inherited(arguments);
  _$jscoverage['dojo/pentaho/common/button.js'][25]++;
  dojo.connect(this.button, "onclick", this, this.onClick);
})});

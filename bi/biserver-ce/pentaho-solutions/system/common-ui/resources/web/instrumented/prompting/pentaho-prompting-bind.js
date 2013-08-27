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
if (! _$jscoverage['prompting/pentaho-prompting-bind.js']) {
  _$jscoverage['prompting/pentaho-prompting-bind.js'] = [];
  _$jscoverage['prompting/pentaho-prompting-bind.js'][9] = 0;
  _$jscoverage['prompting/pentaho-prompting-bind.js'][10] = 0;
  _$jscoverage['prompting/pentaho-prompting-bind.js'][11] = 0;
  _$jscoverage['prompting/pentaho-prompting-bind.js'][13] = 0;
  _$jscoverage['prompting/pentaho-prompting-bind.js'][14] = 0;
}
_$jscoverage['prompting/pentaho-prompting-bind.js'].source = ["<span class=\"c\">// Implementation of the .bind method now included in ECMAScript 5th Edition</span>","<span class=\"c\">// (This is the exact implementation from Prototype.js)</span>","<span class=\"c\">//</span>","<span class=\"c\">// Used to encapsulate scope for a function call:</span>","<span class=\"c\">// (function(a, b) {</span>","<span class=\"c\">//   return this.doSomething(a) + b;</span>","<span class=\"c\">// }).bind(this);</span>","<span class=\"c\">//</span>","<span class=\"k\">if</span> <span class=\"k\">(!</span>Function<span class=\"k\">.</span>prototype<span class=\"k\">.</span>bind<span class=\"k\">)</span> <span class=\"k\">{</span> <span class=\"c\">// check if native implementation available</span>","  Function<span class=\"k\">.</span>prototype<span class=\"k\">.</span>bind <span class=\"k\">=</span> <span class=\"k\">function</span><span class=\"k\">()</span><span class=\"k\">{</span>","    <span class=\"k\">var</span> fn <span class=\"k\">=</span> <span class=\"k\">this</span><span class=\"k\">,</span> args <span class=\"k\">=</span> Array<span class=\"k\">.</span>prototype<span class=\"k\">.</span>slice<span class=\"k\">.</span>call<span class=\"k\">(</span>arguments<span class=\"k\">),</span>","      object <span class=\"k\">=</span> args<span class=\"k\">.</span>shift<span class=\"k\">();</span>","    <span class=\"k\">return</span> <span class=\"k\">function</span><span class=\"k\">()</span><span class=\"k\">{</span>","      <span class=\"k\">return</span> fn<span class=\"k\">.</span>apply<span class=\"k\">(</span>object<span class=\"k\">,</span>","        args<span class=\"k\">.</span>concat<span class=\"k\">(</span>Array<span class=\"k\">.</span>prototype<span class=\"k\">.</span>slice<span class=\"k\">.</span>call<span class=\"k\">(</span>arguments<span class=\"k\">)));</span>","    <span class=\"k\">}</span><span class=\"k\">;</span>","  <span class=\"k\">}</span><span class=\"k\">;</span>","<span class=\"k\">}</span>"];
_$jscoverage['prompting/pentaho-prompting-bind.js'][9]++;
if ((! Function.prototype.bind)) {
  _$jscoverage['prompting/pentaho-prompting-bind.js'][10]++;
  Function.prototype.bind = (function () {
  _$jscoverage['prompting/pentaho-prompting-bind.js'][11]++;
  var fn = this, args = Array.prototype.slice.call(arguments), object = args.shift();
  _$jscoverage['prompting/pentaho-prompting-bind.js'][13]++;
  return (function () {
  _$jscoverage['prompting/pentaho-prompting-bind.js'][14]++;
  return fn.apply(object, args.concat(Array.prototype.slice.call(arguments)));
});
});
}
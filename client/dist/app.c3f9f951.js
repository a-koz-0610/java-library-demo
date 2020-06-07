// modules are defined as an array
// [ module function, map of requires ]
//
// map of requires is short require name -> numeric require
//
// anything defined in a previous bundle is accessed via the
// orig method which is the require for previous bundles
parcelRequire = (function (modules, cache, entry, globalName) {
  // Save the require from previous bundle to this closure if any
  var previousRequire = typeof parcelRequire === 'function' && parcelRequire;
  var nodeRequire = typeof require === 'function' && require;

  function newRequire(name, jumped) {
    if (!cache[name]) {
      if (!modules[name]) {
        // if we cannot find the module within our internal map or
        // cache jump to the current global require ie. the last bundle
        // that was added to the page.
        var currentRequire = typeof parcelRequire === 'function' && parcelRequire;
        if (!jumped && currentRequire) {
          return currentRequire(name, true);
        }

        // If there are other bundles on this page the require from the
        // previous one is saved to 'previousRequire'. Repeat this as
        // many times as there are bundles until the module is found or
        // we exhaust the require chain.
        if (previousRequire) {
          return previousRequire(name, true);
        }

        // Try the node require function if it exists.
        if (nodeRequire && typeof name === 'string') {
          return nodeRequire(name);
        }

        var err = new Error('Cannot find module \'' + name + '\'');
        err.code = 'MODULE_NOT_FOUND';
        throw err;
      }

      localRequire.resolve = resolve;
      localRequire.cache = {};

      var module = cache[name] = new newRequire.Module(name);

      modules[name][0].call(module.exports, localRequire, module, module.exports, this);
    }

    return cache[name].exports;

    function localRequire(x){
      return newRequire(localRequire.resolve(x));
    }

    function resolve(x){
      return modules[name][1][x] || x;
    }
  }

  function Module(moduleName) {
    this.id = moduleName;
    this.bundle = newRequire;
    this.exports = {};
  }

  newRequire.isParcelRequire = true;
  newRequire.Module = Module;
  newRequire.modules = modules;
  newRequire.cache = cache;
  newRequire.parent = previousRequire;
  newRequire.register = function (id, exports) {
    modules[id] = [function (require, module) {
      module.exports = exports;
    }, {}];
  };

  var error;
  for (var i = 0; i < entry.length; i++) {
    try {
      newRequire(entry[i]);
    } catch (e) {
      // Save first error but execute all entries
      if (!error) {
        error = e;
      }
    }
  }

  if (entry.length) {
    // Expose entry point to Node, AMD or browser globals
    // Based on https://github.com/ForbesLindesay/umd/blob/master/template.js
    var mainExports = newRequire(entry[entry.length - 1]);

    // CommonJS
    if (typeof exports === "object" && typeof module !== "undefined") {
      module.exports = mainExports;

    // RequireJS
    } else if (typeof define === "function" && define.amd) {
     define(function () {
       return mainExports;
     });

    // <script>
    } else if (globalName) {
      this[globalName] = mainExports;
    }
  }

  // Override the current require with this new one
  parcelRequire = newRequire;

  if (error) {
    // throw error from earlier, _after updating parcelRequire_
    throw error;
  }

  return newRequire;
})({"js/crud/crud.js":[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = void 0;

function getRequest(location, callback) {
  fetch(location).then(function (response) {
    return response.json();
  }).then(function (data) {
    return callback(data);
  }).catch(function (err) {
    return console.log(err);
  });
}

function postRequest(location, requestBody, callback) {
  fetch(location, {
    method: 'POST',
    body: JSON.stringify(requestBody)
  }).then(function (response) {
    return response.json();
  }).then(function (data) {
    return callback(data);
  }).catch(function (err) {
    return console.log(err);
  });
}

var _default = {
  getRequest: getRequest,
  postRequest: postRequest
};
exports.default = _default;
},{}],"js/components/Header.js":[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = Header;

function Header() {
  return "\n    <ul class='nav-list'>\n        <li class='nav-list__home'>Home</li>\n        <li class='nav-list__books'>Books</li>\n        <li class='nav-list__hashtags'>HashTags</li>\n        <li class='nav-list__contact'>Contact Us</li>\n    </ul>\n    ";
}
},{}],"js/components/Books.js":[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = Books;

function Books(books) {
  return "\n    <h1>Books</h1>\n    <ul class=\"books-list\">\n    ".concat(books.map(function (book) {
    return "\n        <li class='books-list__title'>".concat(book.title, "\n            <input type='hidden' id='bookId' value='").concat(book.id, "'>\n        </li>\n        ");
  }).join(''), "\n    </ul>\n    ");
}
},{}],"js/components/Book.js":[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = Book;

function Book(book) {
  return "\n    <h1>".concat(book.title, "</h1>\n    <h2>").concat(book.description, "</h2>\n    <h3>Authors...</h3>\n    \n    <h3>Hashtags...</h3>\n    <ul>\n    ").concat(book.hashTags.map(function (hashTag) {
    return "\n        <li>\n        <span>".concat(hashTag.name, "</span>\n        </li>\n        ");
  }).join(''), "\n    </ul>\n<section class='add-hashtag'>\n    <input class='add-hashtag__name' type='text' placeholder='Attach a Hashtag' />\n    <button class='add-hashtag__bookSubmit'>Submit</button>\n    <input type='hidden' id='bookId' value='").concat(book.id, "' />\n</section>\n    ");
}
},{}],"js/components/Footer.js":[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = Footer;

function Footer() {
  return "\n    <small>&copy wcci 2020</small>\n    <ul class='social-list'>\n        <li>GitHub</li>\n        <li>LinkedIn</li>\n    </ul>\n    ";
}
},{}],"assets/books.jpg":[function(require,module,exports) {
module.exports = "/books.8316684c.jpg";
},{}],"js/components/Home.js":[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = Home;

var _books = _interopRequireDefault(require("../../assets/books.jpg"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function Home() {
  return "\n        <img class=\"page-image\" src=\"".concat(_books.default, "\" alt=\"books\" >\n    ");
}
},{"../../assets/books.jpg":"assets/books.jpg"}],"assets/hello.jpg":[function(require,module,exports) {
module.exports = "/hello.b2448e2f.jpg";
},{}],"js/components/Contact.js":[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = Contact;

var _hello = _interopRequireDefault(require("../../assets/hello.jpg"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function Contact() {
  return "\n    <h1>Contact Us</h1>\n    <img class=\"page-image\" src=\"".concat(_hello.default, "\" alt=\"books\">\n    <article>\n        <h4>Contact Us Today</h4>\n        <p>(555)555-5555</p>\n    </article>\n    ");
}
},{"../../assets/hello.jpg":"assets/hello.jpg"}],"js/components/HashTags.js":[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = HashTags;

function HashTags(hashTags) {
  return "\n    <h1>Saved HashTags</h1>\n    <ul class='hashTags-list'>\n    ".concat(hashTags.map(function (hashTag) {
    return "\n        <li class='hashTags-list__name'>".concat(hashTag.name, "</li>\n        ");
  }).join(''), "\n    </ul>\n\n    <section class='add-hashtag'>\n        <input class='add-hashtag__name' type='text' placeholder='Add a Hashtag to Save' />\n        <button class='add-hashtag__submit'>Save</button>\n    </section>\n    ");
}
},{}],"node_modules/parcel-bundler/src/builtins/bundle-url.js":[function(require,module,exports) {
var bundleURL = null;

function getBundleURLCached() {
  if (!bundleURL) {
    bundleURL = getBundleURL();
  }

  return bundleURL;
}

function getBundleURL() {
  // Attempt to find the URL of the current script and use that as the base URL
  try {
    throw new Error();
  } catch (err) {
    var matches = ('' + err.stack).match(/(https?|file|ftp|chrome-extension|moz-extension):\/\/[^)\n]+/g);

    if (matches) {
      return getBaseURL(matches[0]);
    }
  }

  return '/';
}

function getBaseURL(url) {
  return ('' + url).replace(/^((?:https?|file|ftp|chrome-extension|moz-extension):\/\/.+)\/[^/]+$/, '$1') + '/';
}

exports.getBundleURL = getBundleURLCached;
exports.getBaseURL = getBaseURL;
},{}],"node_modules/parcel-bundler/src/builtins/css-loader.js":[function(require,module,exports) {
var bundle = require('./bundle-url');

function updateLink(link) {
  var newLink = link.cloneNode();

  newLink.onload = function () {
    link.remove();
  };

  newLink.href = link.href.split('?')[0] + '?' + Date.now();
  link.parentNode.insertBefore(newLink, link.nextSibling);
}

var cssTimeout = null;

function reloadCSS() {
  if (cssTimeout) {
    return;
  }

  cssTimeout = setTimeout(function () {
    var links = document.querySelectorAll('link[rel="stylesheet"]');

    for (var i = 0; i < links.length; i++) {
      if (bundle.getBaseURL(links[i].href) === bundle.getBundleURL()) {
        updateLink(links[i]);
      }
    }

    cssTimeout = null;
  }, 50);
}

module.exports = reloadCSS;
},{"./bundle-url":"node_modules/parcel-bundler/src/builtins/bundle-url.js"}],"css/style.css":[function(require,module,exports) {
var reloadCSS = require('_css_loader');

module.hot.dispose(reloadCSS);
module.hot.accept(reloadCSS);
},{"_css_loader":"node_modules/parcel-bundler/src/builtins/css-loader.js"}],"css/layout.css":[function(require,module,exports) {
var reloadCSS = require('_css_loader');

module.hot.dispose(reloadCSS);
module.hot.accept(reloadCSS);
},{"_css_loader":"node_modules/parcel-bundler/src/builtins/css-loader.js"}],"js/app.js":[function(require,module,exports) {
"use strict";

var _crud = _interopRequireDefault(require("./crud/crud"));

var _Header = _interopRequireDefault(require("./components/Header"));

var _Books = _interopRequireDefault(require("./components/Books"));

var _Book = _interopRequireDefault(require("./components/Book"));

var _Footer = _interopRequireDefault(require("./components/Footer"));

var _Home = _interopRequireDefault(require("./components/Home"));

var _Contact = _interopRequireDefault(require("./components/Contact"));

var _HashTags = _interopRequireDefault(require("./components/HashTags"));

require("../css/style.css");

require("../css/layout.css");

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

buildPage();

function buildPage() {
  header();
  navBooks();
  navHashTags();
  home();
  contact();
  footer();
}

function header() {
  var headerElem = document.querySelector('.header');
  headerElem.innerHTML = (0, _Header.default)();
}

function home() {
  var homeElem = document.querySelector('.nav-list__home');
  homeElem.addEventListener('click', function () {
    var app = document.querySelector('#app');
    app.innerHTML = (0, _Home.default)();
  });
}

function contact() {
  var contactElem = document.querySelector('.nav-list__contact');
  contactElem.addEventListener('click', function () {
    var app = document.querySelector('#app');
    app.innerHTML = (0, _Contact.default)();
  });
}

function navBooks() {
  var booksLi = document.querySelector('.nav-list__books');
  booksLi.addEventListener('click', function () {
    var app = document.querySelector('#app');

    _crud.default.getRequest('http://localhost:8080/rest-books', function (books) {
      app.innerHTML = (0, _Books.default)(books);
    });

    renderBookInfo();
  });
}

function navHashTags() {
  var hashTagsLi = document.querySelector('.nav-list__hashtags');
  hashTagsLi.addEventListener('click', function () {
    var app = document.querySelector('#app');

    _crud.default.getRequest('http://localhost:8080/rest-hashtags', function (hashTags) {
      app.innerHTML = (0, _HashTags.default)(hashTags);
    });
  });
  app.addEventListener('click', function () {
    if (event.target.classList.contains('add-hashtag__submit')) {
      var hashTagName = event.target.parentElement.querySelector('.add-hashtag__name').value;
      console.log(hashTagName);

      _crud.default.postRequest('http://localhost:8080/rest-hashtags/add', {
        hashTagName: hashTagName
      }, function (hashTags) {
        return app.innerHTML = (0, _HashTags.default)(hashTags);
      });
    }
  });
}

function renderBookInfo() {
  // const app = document.querySelector('#app');
  app.addEventListener('click', function () {
    if (event.target.classList.contains('books-list__title')) {
      var bookId = event.target.querySelector('#bookId').value;

      _crud.default.getRequest("http://localhost:8080/rest-books/".concat(bookId), function (book) {
        app.innerHTML = (0, _Book.default)(book);
      });
    }
  });
  app.addEventListener('click', function () {
    if (event.target.classList.contains('add-hashtag__bookSubmit')) {
      var bookId = event.target.parentElement.querySelector('#bookId').value;
      var hashTagName = event.target.parentElement.querySelector('.add-hashtag__name').value;
      console.log('bookID', bookId);
      console.log('name', hashTagName);

      _crud.default.postRequest("http://localhost:8080/rest-books/".concat(bookId, "/add-hashtag"), {
        hashTagName: hashTagName
      }, function (book) {
        return app.innerHTML = (0, _Book.default)(book);
      });
    }
  });
}

function footer() {
  var footerElem = document.querySelector('.footer');
  footerElem.innerHTML = (0, _Footer.default)();
}
},{"./crud/crud":"js/crud/crud.js","./components/Header":"js/components/Header.js","./components/Books":"js/components/Books.js","./components/Book":"js/components/Book.js","./components/Footer":"js/components/Footer.js","./components/Home":"js/components/Home.js","./components/Contact":"js/components/Contact.js","./components/HashTags":"js/components/HashTags.js","../css/style.css":"css/style.css","../css/layout.css":"css/layout.css"}],"node_modules/parcel-bundler/src/builtins/hmr-runtime.js":[function(require,module,exports) {
var global = arguments[3];
var OVERLAY_ID = '__parcel__error__overlay__';
var OldModule = module.bundle.Module;

function Module(moduleName) {
  OldModule.call(this, moduleName);
  this.hot = {
    data: module.bundle.hotData,
    _acceptCallbacks: [],
    _disposeCallbacks: [],
    accept: function (fn) {
      this._acceptCallbacks.push(fn || function () {});
    },
    dispose: function (fn) {
      this._disposeCallbacks.push(fn);
    }
  };
  module.bundle.hotData = null;
}

module.bundle.Module = Module;
var checkedAssets, assetsToAccept;
var parent = module.bundle.parent;

if ((!parent || !parent.isParcelRequire) && typeof WebSocket !== 'undefined') {
  var hostname = "" || location.hostname;
  var protocol = location.protocol === 'https:' ? 'wss' : 'ws';
  var ws = new WebSocket(protocol + '://' + hostname + ':' + "50074" + '/');

  ws.onmessage = function (event) {
    checkedAssets = {};
    assetsToAccept = [];
    var data = JSON.parse(event.data);

    if (data.type === 'update') {
      var handled = false;
      data.assets.forEach(function (asset) {
        if (!asset.isNew) {
          var didAccept = hmrAcceptCheck(global.parcelRequire, asset.id);

          if (didAccept) {
            handled = true;
          }
        }
      }); // Enable HMR for CSS by default.

      handled = handled || data.assets.every(function (asset) {
        return asset.type === 'css' && asset.generated.js;
      });

      if (handled) {
        console.clear();
        data.assets.forEach(function (asset) {
          hmrApply(global.parcelRequire, asset);
        });
        assetsToAccept.forEach(function (v) {
          hmrAcceptRun(v[0], v[1]);
        });
      } else if (location.reload) {
        // `location` global exists in a web worker context but lacks `.reload()` function.
        location.reload();
      }
    }

    if (data.type === 'reload') {
      ws.close();

      ws.onclose = function () {
        location.reload();
      };
    }

    if (data.type === 'error-resolved') {
      console.log('[parcel] ✨ Error resolved');
      removeErrorOverlay();
    }

    if (data.type === 'error') {
      console.error('[parcel] 🚨  ' + data.error.message + '\n' + data.error.stack);
      removeErrorOverlay();
      var overlay = createErrorOverlay(data);
      document.body.appendChild(overlay);
    }
  };
}

function removeErrorOverlay() {
  var overlay = document.getElementById(OVERLAY_ID);

  if (overlay) {
    overlay.remove();
  }
}

function createErrorOverlay(data) {
  var overlay = document.createElement('div');
  overlay.id = OVERLAY_ID; // html encode message and stack trace

  var message = document.createElement('div');
  var stackTrace = document.createElement('pre');
  message.innerText = data.error.message;
  stackTrace.innerText = data.error.stack;
  overlay.innerHTML = '<div style="background: black; font-size: 16px; color: white; position: fixed; height: 100%; width: 100%; top: 0px; left: 0px; padding: 30px; opacity: 0.85; font-family: Menlo, Consolas, monospace; z-index: 9999;">' + '<span style="background: red; padding: 2px 4px; border-radius: 2px;">ERROR</span>' + '<span style="top: 2px; margin-left: 5px; position: relative;">🚨</span>' + '<div style="font-size: 18px; font-weight: bold; margin-top: 20px;">' + message.innerHTML + '</div>' + '<pre>' + stackTrace.innerHTML + '</pre>' + '</div>';
  return overlay;
}

function getParents(bundle, id) {
  var modules = bundle.modules;

  if (!modules) {
    return [];
  }

  var parents = [];
  var k, d, dep;

  for (k in modules) {
    for (d in modules[k][1]) {
      dep = modules[k][1][d];

      if (dep === id || Array.isArray(dep) && dep[dep.length - 1] === id) {
        parents.push(k);
      }
    }
  }

  if (bundle.parent) {
    parents = parents.concat(getParents(bundle.parent, id));
  }

  return parents;
}

function hmrApply(bundle, asset) {
  var modules = bundle.modules;

  if (!modules) {
    return;
  }

  if (modules[asset.id] || !bundle.parent) {
    var fn = new Function('require', 'module', 'exports', asset.generated.js);
    asset.isNew = !modules[asset.id];
    modules[asset.id] = [fn, asset.deps];
  } else if (bundle.parent) {
    hmrApply(bundle.parent, asset);
  }
}

function hmrAcceptCheck(bundle, id) {
  var modules = bundle.modules;

  if (!modules) {
    return;
  }

  if (!modules[id] && bundle.parent) {
    return hmrAcceptCheck(bundle.parent, id);
  }

  if (checkedAssets[id]) {
    return;
  }

  checkedAssets[id] = true;
  var cached = bundle.cache[id];
  assetsToAccept.push([bundle, id]);

  if (cached && cached.hot && cached.hot._acceptCallbacks.length) {
    return true;
  }

  return getParents(global.parcelRequire, id).some(function (id) {
    return hmrAcceptCheck(global.parcelRequire, id);
  });
}

function hmrAcceptRun(bundle, id) {
  var cached = bundle.cache[id];
  bundle.hotData = {};

  if (cached) {
    cached.hot.data = bundle.hotData;
  }

  if (cached && cached.hot && cached.hot._disposeCallbacks.length) {
    cached.hot._disposeCallbacks.forEach(function (cb) {
      cb(bundle.hotData);
    });
  }

  delete bundle.cache[id];
  bundle(id);
  cached = bundle.cache[id];

  if (cached && cached.hot && cached.hot._acceptCallbacks.length) {
    cached.hot._acceptCallbacks.forEach(function (cb) {
      cb();
    });

    return true;
  }
}
},{}]},{},["node_modules/parcel-bundler/src/builtins/hmr-runtime.js","js/app.js"], null)
//# sourceMappingURL=/app.c3f9f951.js.map
(self.webpackChunkchicken_feet_web=self.webpackChunkchicken_feet_web||[]).push([[8991],{8991:(e,t,o)=>{"use strict";o.r(t),o.d(t,{startTapClick:()=>s});var n=o(2377);const s=e=>{let t,o,s,v,f=10*-u,p=0;const h=e.getBoolean("animated",!0)&&e.getBoolean("rippleEffect",!0),L=new WeakMap,m=e=>{f=(0,n.o)(e),k(e)},w=()=>{clearTimeout(v),v=void 0,o&&(S(!1),o=void 0)},E=e=>{o||void 0!==t&&null!==t.parentElement||(t=void 0,b(i(e),e))},k=e=>{b(void 0,e)},b=(e,t)=>{if(e&&e===o)return;clearTimeout(v),v=void 0;const{x:s,y:i}=(0,n.p)(t);if(o){if(L.has(o))throw new Error("internal error");o.classList.contains(r)||g(o,s,i),S(!0)}if(e){const t=L.get(e);t&&(clearTimeout(t),L.delete(e));const o=a(e)?0:d;e.classList.remove(r),v=setTimeout(()=>{g(e,s,i),v=void 0},o)}o=e},g=(e,t,o)=>{p=Date.now(),e.classList.add(r);const n=h&&c(e);n&&n.addRipple&&(T(),s=n.addRipple(t,o))},T=()=>{void 0!==s&&(s.then(e=>e()),s=void 0)},S=e=>{T();const t=o;if(!t)return;const n=l-Date.now()+p;if(e&&n>0&&!a(t)){const e=setTimeout(()=>{t.classList.remove(r),L.delete(t)},l);L.set(t,e)}else t.classList.remove(r)},C=document;C.addEventListener("ionScrollStart",e=>{t=e.target,w()}),C.addEventListener("ionScrollEnd",()=>{t=void 0}),C.addEventListener("ionGestureCaptured",w),C.addEventListener("touchstart",e=>{f=(0,n.o)(e),E(e)},!0),C.addEventListener("touchcancel",m,!0),C.addEventListener("touchend",m,!0),C.addEventListener("mousedown",e=>{const t=(0,n.o)(e)-u;f<t&&E(e)},!0),C.addEventListener("mouseup",e=>{const t=(0,n.o)(e)-u;f<t&&k(e)},!0)},i=e=>{if(!e.composedPath)return e.target.closest(".ion-activatable");{const t=e.composedPath();for(let e=0;e<t.length-2;e++){const o=t[e];if(o.classList&&o.classList.contains("ion-activatable"))return o}}},a=e=>e.classList.contains("ion-activatable-instant"),c=e=>{if(e.shadowRoot){const t=e.shadowRoot.querySelector("ion-ripple-effect");if(t)return t}return e.querySelector("ion-ripple-effect")},r="ion-activated",d=200,l=200,u=2500}}]);
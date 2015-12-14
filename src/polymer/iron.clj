(ns polymer.iron
  (:refer-clojure :exclude [list meta])
  (:require [miraj.markup :refer [make-tag-fns]]))

(alter-meta! *ns* (fn [m] (assoc m :co-ns true)))

(def polymer-iron-tags
  ["a11y-announcer"
   "a11y-keys"
   "a11y-keys-behavior"
   "ajax"
   "autogrow-textarea"
   "behaviors"
   "checked-element-behavior"
   "collapse"
   "component-page"
   "doc-viewer"
   "dropdown"
   "fit-behavior"
   "flex-layout"
   "form"
   "form-element-behavior"
   "icon"
   "icons"
   "iconset"
   "iconset-svg"
   "image"
   "input"
   "jsonp-library"
   "label"
   "list"
   "localstorage"
   "media-query"
   "menu-behavior"
   "meta"
   "overlay-behavior"
   "pages"
   "range-behavior"
   "resizable-behavior"
   "selector"
   "signals"
   "swipeable-container"
   "test-helpers"
   "validatable-behavior"
   "validator-behavior"])

(make-tag-fns "iron-" polymer-iron-tags nil)
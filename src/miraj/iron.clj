(ns miraj.iron
  (:refer-clojure :exclude [list meta]))
  ;; (:require [miraj.markup :refer [make-resource-fns]]))

(alter-meta! *ns*
             (fn [m] (assoc m
                            :co-ns true
                            :resource-type :polymer
                            :resource-pfx "bower_components")))

(def pfx "bower_components")

(def components
  {:a11y-announcer [:iron-a11y-announcer "iron-a11y-announcer/iron-a11y-announcer.html"]
   :a11y-keys [:iron-a11y-keys "iron-a11y-keys/iron-a11y-keys.html"]
   :a11y-keys-behavior [:iron-a11y-keys-behavior "iron-a11y-keys-behavior/iron-a11y-keys-behavior.html"]
   :ajax [:iron-ajax "iron-ajax/iron-ajax.html"]
   :autogrow-textarea [:iron-autogrow-textarea "iron-autogrow-textarea/iron-autogrow-textarea.html"]
   :behaviors [:iron-behaviors "iron-behaviors/iron-behaviors.html"]
   :checked-element-behavior [:iron-checked-element-behavior "iron-checked-element-behavior/iron-checked-element-behavior.html"]
   :collapse [:iron-collapse "iron-collapse/iron-collapse.html"]
   :component-page [:iron-component-page "iron-component-page/iron-component-page.html"]
   :doc-viewer [:iron-doc-viewer "iron-doc-viewer/iron-doc-viewer.html"]
   :dropdown [:iron-dropdown "iron-dropdown/iron-dropdown.html"]
   :fit-behavior [:iron-fit-behavior "iron-fit-behavior/iron-fit-behavior.html"]
   :flex-layout [:iron-flex-layout "iron-flex-layout/iron-flex-layout.html"]
   :form [:iron-form "iron-form/iron-form.html"]
   :form-element-behavior [:iron-form-element-behavior "iron-form-element-behavior/iron-form-element-behavior.html"]
   :icon [:iron-icon "iron-icon/iron-icon.html"]
   :icons [:iron-icons "iron-icons/iron-icons.html"]
   :iconset [:iron-iconset "iron-iconset/iron-iconset.html"]
   :iconset-svg [:iron-iconset-svg "iron-iconset-svg/iron-iconset-svg.html"]
   :image [:iron-image "iron-image/iron-image.html"]
   :input [:iron-input "iron-input/iron-input.html"]
   :jsonp-library [:iron-jsonp-library "iron-jsonp-library/iron-jsonp-library.html"]
   :label [:iron-label "iron-label/iron-label.html"]
   :list [:iron-list "iron-list/iron-list.html"]
   :localstorage [:iron-localstorage "iron-localstorage/iron-localstorage.html"]
   :media-query [:iron-media-query "iron-media-query/iron-media-query.html"]
   :menu-behavior [:iron-menu-behavior "iron-menu-behavior/iron-menu-behavior.html"]
   :meta [:iron-meta "iron-meta/iron-meta.html"]
   :overlay-behavior [:iron-overlay-behavior "iron-overlay-behavior/iron-overlay-behavior.html"]
   :pages [:iron-pages "iron-pages/iron-pages.html"]
   :range-behavior [:iron-range-behavior "iron-range-behavior/iron-range-behavior.html"]
   :request [:iron-request "iron-ajax/iron-request.html"]
   :resizable-behavior [:iron-resizable-behavior "iron-resizable-behavior/iron-resizable-behavior.html"]
   :selector [:iron-selector "iron-selector/iron-selector.html"]
   :signals [:iron-signals "iron-signals/iron-signals.html"]
   :swipeable-container [:iron-swipeable-container "iron-swipeable-container/iron-swipeable-container.html"]
   :test-helpers [:iron-test-helpers "iron-test-helpers/iron-test-helpers.html"]
   :validatable-behavior [:iron-validatable-behavior "iron-validatable-behavior/iron-validatable-behavior.html"]
   :validator-behavior [:iron-validator-behavior "iron-validator-behavior/iron-validator-behavior.html"]})

#_(def polymer-iron-tags
  [['a11y-announcer	:iron-a11y-announcer	"iron-a11y-announcer/iron-a11y-announcer.html"	"tag: <iron-a11y-announcer> uri: iron-a11y-announcer/iron-a11y-announcer.html"]
   ['a11y-keys	:iron-a11y-keys	"iron-a11y-keys/iron-a11y-keys.html"	"tag: <iron-a11y-keys> uri: iron-a11y-keys/iron-a11y-keys.html"]
   ['a11y-keys-behavior	:iron-a11y-keys-behavior	"iron-a11y-keys-behavior/iron-a11y-keys-behavior.html"	"tag: <iron-a11y-keys-behavior> uri: iron-a11y-keys-behavior/iron-a11y-keys-behavior.html"]
   ['ajax	:iron-ajax	"iron-ajax/iron-ajax.html"	"tag: <iron-ajax> uri: iron-ajax/iron-ajax.html"]
   ['autogrow-textarea	:iron-autogrow-textarea	"iron-autogrow-textarea/iron-autogrow-textarea.html"	"tag: <iron-autogrow-textarea> uri: iron-autogrow-textarea/iron-autogrow-textarea.html"]
   ['behaviors	:iron-behaviors	"iron-behaviors/iron-behaviors.html"	"tag: <iron-behaviors> uri: iron-behaviors/iron-behaviors.html"]
   ['checked-element-behavior	:iron-checked-element-behavior	"iron-checked-element-behavior/iron-checked-element-behavior.html"	"tag: <iron-checked-element-behavior> uri: iron-checked-element-behavior/iron-checked-element-behavior.html"]
   ['collapse	:iron-collapse	"iron-collapse/iron-collapse.html"	"tag: <iron-collapse> uri: iron-collapse/iron-collapse.html"]
   ['component-page	:iron-component-page	"iron-component-page/iron-component-page.html"	"tag: <iron-component-page> uri: iron-component-page/iron-component-page.html"]
   ['doc-viewer	:iron-doc-viewer	"iron-doc-viewer/iron-doc-viewer.html"	"tag: <iron-doc-viewer> uri: iron-doc-viewer/iron-doc-viewer.html"]
   ['dropdown	:iron-dropdown	"iron-dropdown/iron-dropdown.html"	"tag: <iron-dropdown> uri: iron-dropdown/iron-dropdown.html"]
   ['fit-behavior	:iron-fit-behavior	"iron-fit-behavior/iron-fit-behavior.html"	"tag: <iron-fit-behavior> uri: iron-fit-behavior/iron-fit-behavior.html"]
   ['flex-layout	:iron-flex-layout	"iron-flex-layout/iron-flex-layout.html"	"tag: <iron-flex-layout> uri: iron-flex-layout/iron-flex-layout.html"]
   ['form	:iron-form	"iron-form/iron-form.html"	"tag: <iron-form> uri: iron-form/iron-form.html"]
   ['form-element-behavior	:iron-form-element-behavior	"iron-form-element-behavior/iron-form-element-behavior.html"	"tag: <iron-form-element-behavior> uri: iron-form-element-behavior/iron-form-element-behavior.html"]
   ['icon	:iron-icon	"iron-icon/iron-icon.html"	"tag: <iron-icon> uri: iron-icon/iron-icon.html"]
   ['icons	:iron-icons	"iron-icons/iron-icons.html"	"tag: <iron-icons> uri: iron-icons/iron-icons.html"]
   ['iconset	:iron-iconset	"iron-iconset/iron-iconset.html"	"tag: <iron-iconset> uri: iron-iconset/iron-iconset.html"]
   ['iconset-svg	:iron-iconset-svg	"iron-iconset-svg/iron-iconset-svg.html"	"tag: <iron-iconset-svg> uri: iron-iconset-svg/iron-iconset-svg.html"]
   ['image	:iron-image	"iron-image/iron-image.html"	"tag: <iron-image> uri: iron-image/iron-image.html"]
   ['input	:iron-input	"iron-input/iron-input.html"	"tag: <iron-input> uri: iron-input/iron-input.html"]
   ['jsonp-library	:iron-jsonp-library	"iron-jsonp-library/iron-jsonp-library.html"	"tag: <iron-jsonp-library> uri: iron-jsonp-library/iron-jsonp-library.html"]
   ['label	:iron-label	"iron-label/iron-label.html"	"tag: <iron-label> uri: iron-label/iron-label.html"]
   ['list	:iron-list	"iron-list/iron-list.html"	"tag: <iron-list> uri: iron-list/iron-list.html"]
   ['localstorage	:iron-localstorage	"iron-localstorage/iron-localstorage.html"	"tag: <iron-localstorage> uri: iron-localstorage/iron-localstorage.html"]
   ['media-query	:iron-media-query	"iron-media-query/iron-media-query.html"	"tag: <iron-media-query> uri: iron-media-query/iron-media-query.html"]
   ['menu-behavior	:iron-menu-behavior	"iron-menu-behavior/iron-menu-behavior.html"	"tag: <iron-menu-behavior> uri: iron-menu-behavior/iron-menu-behavior.html"]
   ['meta	:iron-meta	"iron-meta/iron-meta.html"	"tag: <iron-meta> uri: iron-meta/iron-meta.html"]
   ['overlay-behavior	:iron-overlay-behavior	"iron-overlay-behavior/iron-overlay-behavior.html"	"tag: <iron-overlay-behavior> uri: iron-overlay-behavior/iron-overlay-behavior.html"]
   ['pages	:iron-pages	"iron-pages/iron-pages.html"	"tag: <iron-pages> uri: iron-pages/iron-pages.html"]
   ['range-behavior	:iron-range-behavior	"iron-range-behavior/iron-range-behavior.html"	"tag: <iron-range-behavior> uri: iron-range-behavior/iron-range-behavior.html"]
   ['resizable-behavior	:iron-resizable-behavior	"iron-resizable-behavior/iron-resizable-behavior.html"	"tag: <iron-resizable-behavior> uri: iron-resizable-behavior/iron-resizable-behavior.html"]
   ['selector	:iron-selector	"iron-selector/iron-selector.html"	"tag: <iron-selector> uri: iron-selector/iron-selector.html"]
   ['signals	:iron-signals	"iron-signals/iron-signals.html"	"tag: <iron-signals> uri: iron-signals/iron-signals.html"]
   ['swipeable-container	:iron-swipeable-container	"iron-swipeable-container/iron-swipeable-container.html"	"tag: <iron-swipeable-container> uri: iron-swipeable-container/iron-swipeable-container.html"]
   ['test-helpers	:iron-test-helpers	"iron-test-helpers/iron-test-helpers.html"	"tag: <iron-test-helpers> uri: iron-test-helpers/iron-test-helpers.html"]
   ['validatable-behavior	:iron-validatable-behavior	"iron-validatable-behavior/iron-validatable-behavior.html"	"tag: <iron-validatable-behavior> uri: iron-validatable-behavior/iron-validatable-behavior.html"]
   ['validator-behavior	:iron-validator-behavior	"iron-validator-behavior/iron-validator-behavior.html"	"tag: <iron-validator-behavior> uri: iron-validator-behavior/iron-validator-behavior.html"]])

#_(make-resource-fns :html polymer-iron-tags)

;;(make-tag-fns "iron-" polymer-iron-tags nil)

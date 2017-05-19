(ns miraj.polymer.iron.demos.lists.cards
  (:require [miraj.core :refer [defpage]]
            [miraj.html :as h]
            [miraj.polymer.dom :as dom :refer :all]
            [cheshire.core :as json :refer :all]

            ;; for testing only:
            [miraj.co-dom :as x]
            :reload))

(defpage
  "Polymer Iron Lists Demo."

  {::h/title "Polymer Iron Lists Demo"
   ::h/description "This list demonstrates a Polymer iron-lists element."
   :viewport {:content "width=device-width, minimum-scale=1.0, initial-scale=1, user-scalable=yes"}
   :mobile {:web-app-capable true}
   ;; <meta name="apple-mobile-web-app-capable" content="yes">
   }

  (:require [miraj.polymer.iron :as iron :refer [ajax image list]]
            [miraj.polymer.paper :as paper :refer [icon-button]]
            [miraj.polymer.layout :as layout :refer [header toolbar]])

  (:styles [[miraj.polymer.iron.styles flex-mixins]])

  (:body
   :!unresolved

   ;; (h/template {:is "dom-bind"}
   ((dom/lambda []
    (iron/ajax :!auto {:url "data/contacts.json" :last-response :data})

    (layout/header :!condenses!fixed {:effects "resize-title blend-background waterfall"}
     (layout/toolbar
      (h/span :foo)
      (paper/icon-button {:icon "menu"} :!drawer-toggle)
      (h/h4 :!condensed-title "iron-list — " (h/i "Demo")) ;; FIXME: &mdash; char ref
      (paper/icon-button {:icon "search"})
      (paper/icon-button {:icon "more-vert"}))

     (layout/toolbar
      :.tall
      (h/h1 :!main-title "iron-list")))

    ;; iron-list using the document scroll
    #_(iron/list {:items 'data :as "item" :scroll-target "html"}
               (h/template
                (h/div
                 (h/div :.item {:tabindex "[[tabIndex]]"}  ;;FIXME: attrib set!
                        (iron/image :.avatar
                                    {:sizing "contain" :src 'item.image})
                        (h/div :.pad
                               (h/div :.primary 'item.name 'index)
                               (h/div :.secondary 'item.shortText)
                               (h/div :.secondary.dim 'item.longText))))))))))

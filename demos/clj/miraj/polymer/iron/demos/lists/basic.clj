(ns miraj.polymer.iron.demos.lists.basic
  (:require [miraj.core :refer [defpage]]
            [miraj.html :as h]
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

  (:require [miraj.polymer.iron :as iron :refer [ajax list]])

  (:body :!unresolved
         (h/header (h/h1 "Header"))
         (h/template {:is "dom-bind"}
                      (iron/ajax :!auto {:url "data/contacts.json"
                                         :last-response :data})
                      (iron/list {:items 'data :as "item"}
                                 (h/template
                                  (h/div :.item
                                         (h/b "#" 'index " - " 'item.name)
                                         (h/p :item.longText)))))))

(ns miraj.polymer.iron.demos.ajax
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [cheshire.core :as json :refer :all]
            ;; for testing only:
            [miraj.compiler :as wc]
            [miraj.co-dom :as x]
            :reload))

(miraj/defpage ^{:miraj/demonstrates miraj.polymer.iron/ajax} index
  "Polymer Iron Ajax Demo."

  ;; html metadata first
  {:html/title "Polymer Iron Ajax Demo"
   :html/description "This page demonstrates a Polymer iron-ajax element."}

  (:require [miraj.polymer.iron :as iron :refer [ajax image]])

  (:styles [[miraj.polymer.paper.styles demo-pages]])

  ;; for boot-reload
  ;; (:js [{:src "main.js"}])

  (:body :!unresolved
  (h/style "
    iron-image {
      background-color: lightgray;
      margin: 1em;
    }
    .horizontal-section {
      max-width: 300px;
      margin-bottom: 24px;
    }
")

  (h/script "
    var app = document.querySelector('#app');

    app.url = function (videoId) {
      return 'https://www.youtube.com/watch?v=' + videoId;
    };
")

   (h/h1 "Polymer " (h/span "<iron-ajax>") " Demo")

   (h/div "The original demo is at "
          (h/a {:href "https://www.webcomponents.org/element/PolymerElements/iron-ajax"}
               "webcomponents.org" ))

   (h/h1 "Video Feed")
   (h/div :.horizontal-section-container
          (h/template :#app {:is "dom-bind"}
                      (iron/ajax :!auto
                                 {:url "https://www.googleapis.com/youtube/v3/search"
                                  :params (json/generate-string
                                           {:part "snippet"
                                            :q "polymer"
                                            :key "AIzaSyAuecFZ9xJXbGDkQYWBmYrtzOGJD-iDIgI"
                                            :type "video"})
                                  :handle-as "json"
                                  :last-response :ajaxResponse})

                      (h/template {:is "dom-repeat" :items 'ajaxResponse.items}
                                  (h/div :.horizontal-section
                                         (h/h2 (h/a {:href "[[url(item.id.videoId)]]"
                                                     :target "_blank"}
                                                    'item.snippet.title))
                                         (iron/image :!preload!fade
                                                       {:src 'item.snippet.thumbnails.high.url
                                                        :width 256
                                                        :height 256
                                                        :sizing "cover"})
                                         (h/p 'item.snippet.description)))))))

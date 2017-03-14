(ns miraj.polymer.iron.demos.pages
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [cheshire.core :as json :refer :all]
            ;; for testing only:
            [miraj.compiler :as wc]
            [miraj.co-dom :as x]
            :reload))

(miraj/defpage ^{:miraj/demonstrates miraj.polymer.iron/pages} index
  "Polymer Iron Pages Demo."

  ;; html metadata first
  {:html/title "Polymer Iron Pages Demo"
   :html/description "This page demonstrates a Polymer iron-pages element."
   :viewport {:content "width=device-width, minimum-scale=1.0, initial-scale=1, user-scalable=yes"}
   :mobile {:web-app-capable true}
  ;; <meta name="apple-mobile-web-app-capable" content="yes">
   }

  (:require [miraj.polymer.iron :as iron :refer [demo-snippet pages]])

  (:styles [[miraj.polymer.paper.styles color]
            [miraj.polymer.iron.styles demo]])

  ;; for boot-reload
  ;; (:js [{:src "main.js"}])

  (:body :!unresolved
   (h/div :.vertical-section-container.centered
          (h/h3 "iron-pages shows only one of its children at a time.")
          (iron/demo-snippet
           (h/template
            (h/style {:is "custom-style"} "
          iron-pages {
            width: 100%;
            height: 200px;
            font-size: 50px;
            color: white;
            text-align: center;
          }
          iron-pages div {
            width: 100%;
            height: 100%;
            padding: 80px 0;
          }
          iron-pages div:nth-child(1) {
            background-color: var(--google-blue-500);
          }
          iron-pages div:nth-child(2) {
            background-color: var(--google-red-500);
          }
          iron-pages div:nth-child(3) {
            background-color: var(--google-green-500);
          }")
            (h/p "Click on a page to advance to the next one.")
            (iron/pages {:selected 0}
                        (h/div "One")
                        (h/div "Two")
                        (h/div "Three"))
            (h/script "
          var pages = document.querySelector('iron-pages');
          pages.addEventListener('click', function(e) {
            pages.selectNext();
          });"))))))

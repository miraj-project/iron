(ns miraj.polymer.iron.demos.collapse
  (:require [miraj.core :as miraj]
            [miraj.html :as h]

            ;; for testing only:
            [miraj.co-dom :as x]
            :reload))

(miraj/defpage index
  "Polymer Iron Collapse Demo."

  ;; html metadata first
  {:html/title "Polymer Iron Collapse Demo"
   :html/description "This page demonstrates a Polymer iron-collapse element."}

  (:require [miraj.polymer.iron :as iron :refer [collapse]])

  (:styles [[miraj.polymer.paper.styles shadow]])

  (:css    [{:href "/bower_components/paper-styles/demo.css"}])

  ;; for boot-reload
  ;; (:js [{:src "main.js"}])

  #_(:styling
     [::.heading] {
                   :padding "10px 15px"
                   :margin-top "20px"
                   :background-color "#f3f3f3"
                   :border "1px solid #dedede"
                   :border-top-left-radius "5px"
                   :border-top-right-radius "5px"
                   :font-size "18px"
                   :cursor "pointer"
                   :-webkit-tap-highlight-color "rgba(0,0,0,0)"
                   :width "100%"
                   :text-align "left"
                   }

     [::.content] {:padding "15px"
                   :border "1px solid #dedede"
                   :border-top "none"
                   :border-bottom-left-radius "5px"
                   :border-bottom-right-radius "5px"

                   :apply "(--shadow-elevation-2dp)"

                   }

     [::collapse3] {:max-height "250px"})
  (css :custom "
      .heading {
        padding: 10px 15px;
        margin-top: 20px;
        background-color: #f3f3f3;
        border: 1px solid #dedede;
        border-top-left-radius: 5px;
        border-top-right-radius: 5px;
        font-size: 18px;
        cursor: pointer;
        -webkit-tap-highlight-color: rgba(0,0,0,0);
        width: 100%;
        text-align: left;
      }

      .content {
        padding: 15px;
        border: 1px solid #dedede;
        border-top: none;
        border-bottom-left-radius: 5px;
        border-bottom-right-radius: 5px;
        @apply(--shadow-elevation-2dp);
      }

      #collapse3 {
        max-height: 250px;
      }")

  (:body :!unresolved

         (h/h1 "Polymer " (h/span "<iron-collapse>") " Demo")

         (h/div "The original demo is at "
                (h/a {:href "https://www.webcomponents.org/element/PolymerElements/iron-collapse"}
                     "webcomponents.org" ))

         (h/template {:is "dom-bind"}
                     (h/button :.heading {:aria-expanded "[[isExpanded(opened1)]]"
                                          :aria-controls "collapse1"
                                          :onclick "toggle('#collapse1')"} "Collapse #1")

                     (iron/collapse :#collapse1 {:tabindex 0 :opened :opened1}
                                    (h/div :.content
                                           (h/div "Lorem ipsum dolor sit amet, per in nusquam nominavi periculis, sit elit oportere ea, id minim maiestatis incorrupte duo. Dolorum verterem ad ius, his et nullam verterem. Eu alia debet usu, an doming tritani est. Vix ad ponderum petentium suavitate, eum eu tempor populo, graece sententiae constituam vim ex. Cu torquatos reprimique neglegentur nec, voluptua periculis has ut, at eos discere deleniti sensibus. Lorem ipsum dolor sit amet, per in nusquam nominavi periculis, sit elit oportere ea, id minim maiestatis incorrupte duo. Dolorum verterem ad ius, his et nullam verterem. Eu alia debet usu, an doming tritani est. Vix ad ponderum petentium suavitate, eum eu tempor populo, graece sententiae constituam vim ex. Cu torquatos reprimique neglegentur nec, voluptua periculis has ut, at eos discere deleniti sensibus.")))

                     (h/button :.heading {:aria-expanded "[[isExpanded(opened2)]]"
                                          :aria-controls "collapse2"
                                          :onclick "toggle('#collapse2')"} "Collapse #2")
                     (iron/collapse :#collapse2 {:tabindex 0 :opened :opened2}
                                    (h/div :.content
                                           (h/div "Pro saepe pertinax ei, ad pri animal labores suscipiantur. Modus commodo minimum eum te, vero utinam assueverit per eu, zril oportere suscipiantur pri te. Partem percipitur deterruisset ad sea, at eam suas luptatum dissentiunt. No error alienum pro, erant senserit ex mei, pri semper alterum no. Ut habemus menandri vulputate mea. Feugiat verterem ut sed. Dolores maiestatis id per. Pro saepe pertinax ei, ad pri animal labores suscipiantur. Modus commodo minimum eum te, vero utinam assueverit per eu, zril oportere suscipiantur pri te. Partem percipitur deterruisset ad sea, at eam suas luptatum dissentiunt. No error alienum pro, erant senserit ex mei, pri semper alterum no. Ut habemus menandri vulputate mea. Feugiat verterem ut sed. Dolores maiestatis id per.")

                                           (h/button :.heading  {:aria-expanded "[[isExpanded(opened3)]]"
                                                                 :aria-controls "collapse3"
                                                                 :onclick "toggle('#collapse3')"}
                                                     "Collapse #3 (horizontal)")

                                           (iron/collapse :#collapse3!horizontal {:tabindex 0 :opened :opened3}
                                                          (h/div :.content
                                                                 (h/div "Iisque perfecto dissentiet cum et, sit ut quot mandamus, ut vim tibique splendide instructior. Id nam odio natum malorum, tibique copiosae expetenda mel ea. Mea melius malorum ut. Ut nec tollit vocent timeam. Facer nonumy numquam id his, munere salutatus consequuntur eum et, eum cotidieque definitionem signiferumque id. Ei oblique graecis patrioque vis, et probatus dignissim inciderint vel. Sed id paulo erroribus, autem semper accusamus in mel. Iisque perfecto dissentiet cum et, sit ut quot mandamus, ut vim tibique splendide instructior. Id nam odio natum malorum, tibique copiosae expetenda mel ea. Mea melius malorum ut. Ut nec tollit vocent timeam. Facer nonumy numquam id his, munere salutatus consequuntur eum et, eum cotidieque definitionem signiferumque id. Ei oblique graecis patrioque vis, et probatus dignissim inciderint vel. Sed id paulo erroribus, autem semper accusamus in mel.")))

                                           (h/button :.heading {:aria-expanded "[[isExpanded(opened4)]]"
                                                                :aria-controls "collapse4"
                                                                :onclick "toggle('#collapse4')"}
                                                     "Collapse #4 (no animation)")

                                           (iron/collapse :#collapse4!no-animation {:tabindex 0 :opened :opened4}
                                                          (h/div :.content
                                                                 (h/div "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."))))))
         (h/script {:type "text/javascript"} "

  function toggle(selector) {
    document.querySelector(selector).toggle();
  }

  document.querySelector('template[is=dom-bind]').isExpanded = function(opened) {
    return String(opened);
  };
")))


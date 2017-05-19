(ns miraj.polymer.iron.demos.input
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            ;; for testing only:
            [miraj.co-dom :as x]
            :reload))

(miraj/defpage index
  "Polymer Iron Input Demo."

  ;; html metadata first
  {:html/title "Polymer Iron Input Demo"
   :html/description "This page demonstrates a Polymer iron-input element."}

  (:require [miraj.polymer.iron :as iron :refer [input]])

  (:styles [[miraj.polymer.paper.styles color demo-pages typography]])

  ;; for boot-reload
  ;; (:js [{:src "main.js"}])

  (:body :!unresolved

   (h/style {:is "custom-style"} "

    .vertical-section {
      @apply(--paper-font-body1);

      line-height: 40px;
    }

    code {
      color: var(--google-grey-700);
    }

    input[is=iron-input] {
      width: 100%;
      box-sizing: border-box;
    }

    input, button {
      font-size: 20px;
      padding: 0.2em;
    }
")

   (h/h1 "Polymer " (h/span "<iron-input>") " Demo")

   (h/div "The original demo is at "
          (h/a {:href "https://www.webcomponents.org/element/PolymerElements/iron-input"}
               "webcomponents.org" ))

   (h/div :#mydiv.vertical-section!vertical-section-container!centered
          (h/template {:is "dom-bind"}
                      (h/p
                           (h/input {:is "iron-input"
                                     :bind-value :bindValue
                                     :value :input->value})
                           (h/br)
                           "bind to " (h/code "bind-value") ": " (h/b 'bindValue)
                           (h/br)
                           "bind to " (h/code "value::input") ": " (h/b :value))

                      (h/p {:on-click "setValue"}
                           "set bind-value to: " (h/input) (h/button {:is "paper-button"
                                                                      :value "bindValue"} "set")
                           (h/br)
                           "set value to: " (h/input) (h/button {:value "value"} "set")))

          (h/p "only allows these characters: " (h/code "!@#0123456789"))

          (h/input :!prevent-invalid-input {:is "iron-input" :allowed-pattern "[!@#0-9]"}))

   (h/script {:type "text/javascript"} "

    var scope = document.querySelector('template[is=dom-bind]');

    scope.setValue = function(event) {
      if (!(event.target instanceof HTMLButtonElement)) {
        return;
      }
      document.querySelector('input[is=iron-input]')[event.target.value] = event.target.previousElementSibling.value;
    }
")))


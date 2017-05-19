(def +project+ 'miraj.polymer/iron)
(def +version+ "1.2.3-SNAPSHOT")

(set-env!
 :resource-paths #{"src/main/clj"}  ;; "dev-resources"}
 :source-paths #{"edn"} ;; "demos/clj"}

 ;; :checkouts '[[miraj/core                    "0.1.0-SNAPSHOT"]
 ;;              [miraj/co-dom                  "1.0.0-SNAPSHOT"]
 ;;              [miraj.polymer/dom             "1.2.3-SNAPSHOT"]
 ;;              ;; [miraj/boot-miraj "0.1.0-SNAPSHOT"]
 ;;              ]

 :repositories #(conj % ["clojars" {:url "https://clojars.org/repo/"}])

 :dependencies   '[[org.clojure/clojure  "1.9.0-alpha16"]
                   [miraj/co-dom         "1.0.0-SNAPSHOT"]
                   [miraj.polymer/assets "1.2.3-SNAPSHOT"]

                   ;; for demos and testing
                   [miraj/core           "0.1.0-SNAPSHOT" :scope "test"]
                   [miraj/html "5.1.0-SNAPSHOT"           :scope "test"]
                   [miraj.polymer/dom "1.2.3-SNAPSHOT"    :scope "test"]
                   ;; [org.clojure/clojurescript "1.9.473"]
                   ;; [hipo "0.5.2"]
                   ;; [adzerk/boot-cljs "2.0.0-OUTPUTFIX" :scope "test"]
                   ;; [adzerk/boot-cljs-repl   "0.3.3"] ;; latest release
                   ;; [adzerk/boot-reload "0.5.1"         :scope "test"] ;; cljs
                   [samestep/boot-refresh "0.1.0"         :scope "test"] ;; clj reloading
                   ;; [com.cemerick/piggieback "0.2.1"    :scope "test"]
                   ;; [weasel                  "0.7.0"    :scope "test"]
                   ;; [org.clojure/tools.nrepl "0.2.12"   :scope "test"]

                   [miraj/boot-miraj     "0.1.0-SNAPSHOT" :scope "test"]

                   ;; for demos
                   [cheshire "5.7.0"                      :scope "test"]
                   [miraj.polymer/paper "1.2.3-SNAPSHOT"  :scope "test"]
                   [miraj.polymer/layout "1.2.3-SNAPSHOT" :scope "test"]

                   [pandeiro/boot-http "0.7.3"            :scope "test"]
                   [adzerk/boot-test     "1.0.7"          :scope "test"]
                   ])

(require '[miraj.boot-miraj :as miraj]
         ;; '[adzerk.boot-cljs      :refer [cljs]]
         ;; '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
         ;; '[adzerk.boot-reload :refer [reload]]
         '[adzerk.boot-test :refer [test]]
         '[samestep.boot-refresh :refer [refresh]]
         '[pandeiro.boot-http :as http :refer :all]
         '[boot.task.built-in :as boot :refer :all])

(task-options!
 repl {:port 8080}
 pom  {:project     +project+
       :version     +version+
       :description "Miraj Polymer Iron markup functions"
       :url         "https://github.com/miraj-project/polymer-iron"
       :scm         {:url "https://github.com/miraj-project/polymer-iron.git"}
       :license     {"EPL" "http://www.eclipse.org/legal/epl-v10.html"}})

(deftask build
  "build a component library"
  []
  (comp
   (miraj/compile :libraries true :debug true :keep true)
   (miraj/compile :styles    true :debug true :keep true)))

(deftask demos
  "build component demos"
  []
  ;; (set-env! :asset-paths #(conj % "dev-resources"))
  (set-env! :source-paths #(conj % "demos/clj"))
  (comp
   ;; (miraj/compile :page 'miraj.polymer.iron.demos.collapse/index
   ;;                :polyfill :lite :debug true :keep true)
   ;; (miraj/compile :page 'miraj.polymer.iron.demos.lists.basic
   ;;              :polyfill :lite
   ;;              :assets :polymer
   ;;              :imports ["basic_style.html"]
   ;;              :keep true
   ;;              :debug true)
   (miraj/compile :page 'miraj.polymer.iron.demos.lists.cards
                  :polyfill :lite
                  :imports ["/miraj/polymer/assets/iron-icons/iron-icons.html"
                            "/miraj/polymer/assets/app-layout/app-scroll-effects/app-scroll-effects.html"
                            "cards_style.html"]
                  :keep true
                  :debug true)
   ;; (miraj/compile :pages true :polyfill :lite :debug true :keep true)
   (miraj/link    :pages true
                  :assets :polymer  ;; copy assets from jar to resource dir
                  :debug true) ;; :keep true)
   (miraj/demo-page :debug true)))

;; plain repl won't do, the target dir will not be on the classpath
(deftask dev
  "repl"
  []
  ;; (set-env! :asset-paths #(conj % "dev-resources"))
  (comp (miraj/compile :libraries true :debug true)
        (miraj/compile :style true :verbose true)
        (target)
        (repl)))

(deftask install-local
  "Build and install component libraries"
  []
  (comp (build)
        (pom)
        (jar)
        (target)
        (install)))

(deftask monitor
  "watch etc."
  []
  (comp (build)
        (watch)
        (notify :audible true)
        ;; (refresh)
        (demos)))

(deftask run-demos
  "compile, link, serve demos"
  []
  (comp
   (build)
   (serve :dir "target" :reload true)
   (watch)
   (notify :audible true)
   (refresh)
   (demos)
   (target)))

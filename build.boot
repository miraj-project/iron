(def +project+ 'miraj.polymer/iron)
(def +version+ "1.2.3-SNAPSHOT")

(set-env!
 :resource-paths #{"dev-resources"}
 :source-paths #{"edn" "demos/clj"}

 :checkouts '[[miraj/core                    "0.1.0-SNAPSHOT"]
              [miraj/co-dom                  "0.1.0-SNAPSHOT"]
              [miraj/boot-miraj "0.1.0-SNAPSHOT"]]

 :dependencies   '[[org.clojure/clojure  RELEASE :scope "provided"]

                   ;; for demos and testing
                   ;; [org.clojure/clojurescript "1.9.473"]
                   ;; [hipo "0.5.2"]
                   ;; [adzerk/boot-cljs "2.0.0-OUTPUTFIX" :scope "test"]
                   ;; [adzerk/boot-cljs-repl   "0.3.3"] ;; latest release
                   ;; [adzerk/boot-reload "0.5.1" :scope "test"] ;; cljs
                   [samestep/boot-refresh "0.1.0" :scope "test"] ;; clj reloading
                   ;; [com.cemerick/piggieback "0.2.1"  :scope "test"]
                   ;; [weasel                  "0.7.0"  :scope "test"]
                   [org.clojure/tools.nrepl "0.2.12" :scope "test"]

                   [miraj/boot-miraj     "0.1.0-SNAPSHOT" :scope "test"]
                   [miraj/core           "0.1.0-SNAPSHOT"]
                   [miraj/co-dom         "0.1.0-SNAPSHOT"]

                   ;; for demos
                   [cheshire "5.7.0"]
                   [miraj.polymer/paper "1.2.3-SNAPSHOT" :scope "test"]

                   [pandeiro/boot-http "0.7.3" :scope "test"]
                   [adzerk/boot-test     "1.0.7" :scope "test"]
                   ])

#_(require '[miraj.boot-miraj :as miraj]
         '[adzerk.boot-test :refer [test]])
(require '[miraj.boot-miraj :as miraj]
         ;; '[adzerk.boot-cljs      :refer [cljs]]
         ;; '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
         ;; '[adzerk.boot-reload :refer [reload]]
         '[adzerk.boot-test :refer [test]]
         '[samestep.boot-refresh :refer [refresh]]
         '[pandeiro.boot-http :as http :refer :all]
         '[boot.task.built-in :as boot :refer :all])

(task-options!
;; aot {:namespace #{'miraj.NSException}}
 repl {:port 8080}
;;  miraj/compile {:library true :style true :verbose true}
 pom  {:project     +project+
       :version     +version+
       :description "Miraj Polymer Iron markup functions"
       :url "https://github.com/mobileink/miraj-project/iron"
       :scm         {:url "https://github.com/miraj-project/miraj.core.git"}
       :license     {"EPL" "http://www.eclipse.org/legal/epl-v10.html"}})

(deftask build
  "build a component library"
  []
  (comp
   (miraj/compile :libraries true :debug true :keep true)
   (miraj/compile :styles    true :debug true :keep true)
   (target)))

(deftask demos
  "build component demos"
  []
  (comp
   ;;(build)
   ;; (refresh)
   (miraj/compile ;; :namespace #{'miraj.polymer.iron.demos.pages}
                  :pages true :debug true :keep true)
   (miraj/link    :pages true :debug true) ;; :keep true)
   (miraj/demo-page :debug true)
   ;; (cljs)
   ;; (sift :to-resource #{#".*\.cljs\.edn"}) ;; keep main.cljs.edn, produced by (cljs)
   (target   :no-clean   true)))

;; plain repl won't do, the target dir will not be on the classpath
(deftask dev
  "repl"
  []
  (set-env! :asset-paths #(conj % "dev-resources"))
  (comp (miraj/compile :libraries true :debug true)
        (miraj/compile :style true :verbose true)
        (target)
        (repl)))

(deftask install-local
  "Build and install component libraries"
  []
  (comp (build)
        (target)
        (pom)
        (jar)
        (target :no-clean true)
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
   (watch) ;; :verbose true)
   ;; (cljs-repl)
   (notify :audible true)
   (demos)
   ;; (refresh)
   ;; (miraj.boot-miraj/compile :keep true :debug true :pages true)
   ;; (miraj.boot-miraj/link    :debug true :pages true)
   ;; (reload) ;; this is not for dev
   ;; (target) ;; :no-clean true)
   ;; (cljs)
   ;; (target :no-clean true)
   (wait)))

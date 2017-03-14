(ns polymer.iron-test
  (:require [clojure.java.io :as io]
            [clojure.test :refer :all]
            [miraj.co-dom :refer :all]
            [miraj.polymer.iron :refer :all :as iron]))

(miraj.co-dom/serialize (iron/collapse))

(miraj.co-dom/serialize :xml (iron/collapse))

(miraj.co-dom/serialize :html (iron/collapse))

(miraj.co-dom/serialize
 (iron/dropdown))

(miraj.co-dom/serialize
 (iron/form))


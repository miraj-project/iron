(ns iron-test
  (:require [polymer.iron :as iron]))

(println (iron/ajax))

(miraj.markup/serialize (iron/collapse))
(miraj.markup/serialize :xml (iron/collapse))
(miraj.markup/serialize :html (iron/collapse))

(miraj.markup/serialize
 (iron/dropdown))

(miraj.markup/serialize
 (iron/form))

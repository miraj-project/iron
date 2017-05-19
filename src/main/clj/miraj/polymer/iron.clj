(ns miraj.polymer.iron
  (:refer-clojure :exclude [list meta])
  (:require [miraj.co-dom :as codom]))

(alter-meta! *ns* (fn [m] (assoc m :miraj/miraj {:miraj/elements true
                                                 :miraj/nss '[]
                                                 :miraj/codom ""
                                                 :miraj/assets
                                                 {:miraj/bower
                                                  [

                                                   ]
                                                   :miraj/base ""}})))

(load "iron_impl")

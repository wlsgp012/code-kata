(ns dojo.design-pattern.14-facade
  (:require [clojure.set :as cset]
            [clojure.string :as s]
            [clojure.walk :as w]))

(defn union
  [a b]
  (cset/union a b))

(defn repl
  [text]
  (s/replace text #"/" ""))

(defn walk
  [i o f]
  (w/walk i o f))

;;;;;;;;;;;;;;;;;;
;; (ns app.somet (:use dojo.design-pattern.14-facade))
;; use this

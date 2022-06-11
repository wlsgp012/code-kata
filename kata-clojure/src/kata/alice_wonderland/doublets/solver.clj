(ns kata.alice-wonderland.doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(def current-path (-> *file*
           (io/file)
           (.getParent)))

(def words (-> (str current-path "/" "words.edn")
               (io/file)
               (.getAbsolutePath)
               (slurp)
               (read-string)))

(defn doublets [word1 word2]
  "make me work")

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

(defn equal-word-except-one-letter
  [a b]
  (if (= (count a) (count b))
    (= 1 (count (filter false? (map = a b))))
    false))

(defn same-lengths
  [length l]
  (filter #(= (count %) length) l))

(defn pick-next
  [prev words results]
  (let [rs (set results)]
    (filter #(not (contains? rs %)) (filter (partial equal-word-except-one-letter prev) words))))

(defn doublets
  ([word1 word2]
   ;;(doublets word1 word2  words []))
   (doublets word1 word2 (same-lengths (count word1) words) []))
  ([word1 word2 words result]
   (let [next (pick-next word1 words result)]
     (case (count next)
       0 (if (not= word1 word2) [] (conj result word1))
       1 (let [n (first next)] (recur n word2 (remove #(= n %) words) (conj result word1)))
       (first (filter seq (map #(doublets % word2 (remove (fn [n] (= % n)) words) (conj result word1)) next)))))))

(comment
  (time (doublets "bank" "loan")))

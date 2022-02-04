(ns dojo.problems-in-4clojure.reverse-a-sequence-23)
;; https://4clojure.oxal.org/#/problem/23

(defn answer [l]
  (reduce #(conj %1 %2)
          '()
          l))

(= (answer [1 2 3 4 5]) [5 4 3 2 1])
(= (answer (sorted-set 5 7 2 7)) '(7 5 2))
(= (answer [[1 2][3 4][5 6]]) [[5 6][3 4][1 2]])

;; others
(fn [xs] (into () xs))

(fn [a-seq]
  (reduce conj '() a-seq))

(ns dojo.problems-in-4clojure.054-partition-a-sequence)
;; https://4clojure.oxal.org/#/problem/54

(defn sol
  [n coll]
  (reduce
   (fn [xs x]
     (if (= n (count (last xs)))
       (conj xs [x])
       (conj (vec (drop-last xs)) (conj (vec (last xs)) x))
       ))
   []
   (drop-last (mod (count coll) n) coll)))

(= (sol 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8)))
(= (sol 2 (range 8)) '((0 1) (2 3) (4 5) (6 7)))
(= (sol 3 (range 8)) '((0 1 2) (3 4 5)))

;; others
(fn p [n c]
  (when (<= n (count c))
    (cons (take n c) (p n (drop n c)))))

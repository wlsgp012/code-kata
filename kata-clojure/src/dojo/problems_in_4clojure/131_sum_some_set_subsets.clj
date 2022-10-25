(ns dojo.problems-in-4clojure.131-sum-some-set-subsets
  (:require [dojo.problems-in-4clojure.085-power-set :refer [power-set]]))

(defn sol [& ss]
  (boolean
   (seq
    (apply clojure.set/intersection
           (map (fn [s]
                  (set (map #(apply + %) (filter seq (power-set s)))))
                ss)))))

(= true (sol #{-1 1 99}
             #{-2 2 888}
             #{-3 3 7777}))
(= false (sol #{1}
              #{2}
              #{3}
              #{4}))
(= true  (sol #{1}))
(= false (sol #{1 -3 51 9}
              #{0}
              #{9 2 81 33}))
(= true  (sol #{1 3 5}
              #{9 11 4}
              #{-3 12 3}
              #{-3 4 -2 10}))
(= false (sol #{-1 -2 -3 -4 -5 -6}
              #{1 2 3 4 5 6 7 8 9}))
(= true  (sol #{1 3 5 7}
              #{2 4 6 8}))
(= true  (sol #{-1 3 -5 7 -9 11 -13 15}
              #{1 -3 5 -7 9 -11 13 -15}
              #{1 -1 2 -2 4 -4 8 -8}))

;; others
(fn [& sets]
  (let [sums (fn [[x & xs]]
               (reduce
                #(into % (cons %2 (map (partial + %2) %)))
                #{x} xs))]
    (not= #{} (reduce
               #(set (filter %1 %2))
               (map (comp sums seq) sets)))))

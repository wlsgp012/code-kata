(ns dojo.problems-in-4clojure.069-merge-with-a-function)
;; https://4clojure.oxal.org/#/problem/69

(defn sol
  [f m & ms]
  (reduce (fn [rm [k v]] (assoc rm k (if (contains? rm k)
                                       (apply f (cons (rm k) (map second v)))
                                       (map second v))))
          m
          (group-by first (reduce #(concat %1 (seq %2)) [] ms))))

(= (sol * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})
   {:a 4, :b 6, :c 20})
(= (sol - {1 10, 2 20} {1 3, 2 10, 3 15})
   {1 7, 2 10, 3 15})
(= (sol concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]})
   {:a [3 4 5], :b [6 7], :c [8 9]})

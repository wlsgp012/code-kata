(ns dojo.problems-in-4clojure.055-count-occurences)
;; https://4clojure.oxal.org/#/problem/55

(defn sol [coll]
  (reduce #(conj %1 [(first %2) (count %2)]) {} (partition-by identity (sort coll))))

(= (sol [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1})
(= (sol [:b :a :b :a :b]) {:a 2, :b 3})
(= (sol '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2})

;; others
(fn [c]
  (let [m (group-by identity c)]
    (zipmap (keys m) (map count (vals m)))))

(fn [x]
  (reduce
   #(assoc %1 %2 (inc (%1 %2 0)))
   {}
   x))

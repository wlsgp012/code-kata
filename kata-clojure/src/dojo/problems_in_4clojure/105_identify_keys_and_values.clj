(ns dojo.problems-in-4clojure.105-identify-keys-and-values)

(defn sol [xs]
  (loop [coll xs result {}]
    (if (empty? coll)
      result
      (let [k (first coll)
            extra (next coll)
            next-result (conj result [k (vec (take-while number? extra))])]
        (recur (drop-while number? extra) next-result)))))

(= {} (sol []))
(= {:a [1]} (sol [:a 1]))
(= {:a [1]
    :b [2]} (sol [:a 1, :b 2]))
(= {:a [1 2 3]
    :b []
    :c [4]} (sol [:a 1 2 3 :b :c 4]))

;; others
#(->> %
      (partition-by keyword?)
      (mapcat (fn [[k :as v]] (if (keyword? k) (interpose [] v) [v])))
      (apply hash-map))

(fn f
  ([xs] (f {} nil xs))
  ([m k [x & xs]]
   (if x
     (if (keyword? x)
       (f (assoc m x []) x xs)
       (f (update-in m [k] conj x) k xs))
     m)))

#(first (reduce (fn [[m k] x]
                  (if (keyword? x)
                    (list (assoc m x []) (conj k x))
                    (list (merge-with conj m {(last k) x}) k)))
                '({} []) %))

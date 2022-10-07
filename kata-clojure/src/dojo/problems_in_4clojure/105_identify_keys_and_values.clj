(ns dojo.problems-in-4clojure.105-identify-keys-and-values)

(defn sol [xs]
  (partition-by #(and (identity  %) (keyword? %)) xs))

(= {} (sol []))
(= {:a [1]} (sol [:a 1]))
(= {:a [1]
    :b [2]} (sol [:a 1, :b 2]))
(= {:a [1 2 3]
    :b []
    :c [4]} (sol [:a 1 2 3 :b :c 4]))

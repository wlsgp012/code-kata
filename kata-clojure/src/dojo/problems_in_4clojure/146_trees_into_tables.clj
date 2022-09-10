(ns dojo.problems-in-4clojure.146-trees-into-tables)

(def sol2
  (fn
    ([m] (sol m []))
    ([m super-keys]
     (reduce (fn [r [k v]]
               (let [karr (conj super-keys k)]
                 (into r (if (not (map? v)) [{karr v}] (sol v karr)))))
             {}
             m))))

(def sol
  (fn [m]
    (into {}
          (mapcat
           (fn [[k v]] (map #(vector [k (first %)] (last %)) v))
           m))))

(= (sol '{a {p 1, q 2}
         b {m 3, n 4}})
   '{[a p] 1, [a q] 2
     [b m] 3, [b n] 4})

(= (sol '{[1] {a b c d}
         [2] {q r s t u v w x}})
   '{[[1] a] b, [[1] c] d,
     [[2] q] r, [[2] s] t,
     [[2] u] v, [[2] w] x})

(= (sol '{m {1 [a b c] 3 nil}})
   '{[m 1] [a b c], [m 3] nil})

;; others

(def o1
  #(apply array-map (reduce concat (for [[x y] % [z w] y]
                                    [[x z] w]))))
(def o2
  (fn [m]
    (into {} (for [[x y] m [z w] y]
               [[x z] w]))))

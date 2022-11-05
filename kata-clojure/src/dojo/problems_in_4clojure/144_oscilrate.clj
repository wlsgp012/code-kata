(ns dojo.problems-in-4clojure.144-oscilrate)

(defn sol [value & [f & fs]]
  (cons value (lazy-seq  (apply sol (f value) (conj (vec fs) f)))))

(= (take 3 (sol 3.14 int double)) [3.14 3 3.0])

(= (take 5 (sol 3 #(- % 3) #(+ 5 %))) [3 0 5 2 7])

(= (take 12 (sol 0 inc dec inc dec inc)) [0 1 0 1 0 1 2 1 2 1 2 3])


;; others
(fn [v & fs]
  (reductions (fn [acc f] (f acc)) v (cycle fs)))

(ns dojo.problems-in-4clojure.098-equivalence-classes)

(defn sol [f xs]
  (reduce #(conj %1 (conj (set (filter (fn [x] (= (f %2) (f x))) xs)) %2))
          #{}
          xs))


(= (sol #(* % %) #{-2 -1 0 1 2})
   #{#{0} #{1 -1} #{2 -2}})

(= (sol #(rem % 3) #{0 1 2 3 4 5 })
   #{#{0 3} #{1 4} #{2 5}})

(= (sol identity #{0 1 2 3 4})
   #{#{0} #{1} #{2} #{3} #{4}})

(= (sol (constantly true) #{0 1 2 3 4})
   #{#{0 1 2 3 4}})

;;others
(fn [f xs]
  (set (map #(-> % second set) (group-by f xs))))

(ns dojo.problems-in-4clojure.153-pairwise-disjoint-sets)

(def sol
  #(= (apply + (map count %))
      (count (apply clojure.set/union %))))

;; wrong answear
(def sol2
  (fn [s]
    (reduce (comp set filter)
            (first s)
            s)))

(= (sol #{#{\U} #{\s} #{\e \R \E} #{\P \L} #{\.}})
   true)

(= (sol #{#{:a :b :c :d :e}
                             #{:a :b :c :d}
                             #{:a :b :c}
                             #{:a :b}
                             #{:a}})
   false)

(= (sol #{#{[1 2 3] [4 5]}
                             #{[1 2] [3 4 5]}
                             #{[1] [2] 3 4 5}
                             #{1 2 [3 4] [5]}})
   true)

(= (sol #{#{'a 'b}
                             #{'c 'd 'e}
                             #{'f 'g 'h 'i}
                             #{''a ''c ''f}})
   true)

(= (sol #{#{'(:x :y :z) '(:x :y) '(:z) '()}
                             #{#{:x :y :z} #{:x :y} #{:z} #{}}
                             #{'[:x :y :z] [:x :y] [:z] [] {}}})
   false)

(= (sol #{#{(= "true") false}
                             #{:yes :no}
                             #{(class 1) 0}
                             #{(symbol "true") 'false}
                             #{(keyword "yes") ::no}
                             #{(class '1) (int \0)}})
   false)

(= (sol (set [(set [distinct?])
                                 (set [#(-> %) #(-> %)])
                                 (set [#(-> %) #(-> %) #(-> %)])
                                 (set [#(-> %) #(-> %) #(-> %)])]))
   true)

(= (sol #{#{(#(-> *)) + (quote mapcat) #_ nil}
                             #{'+ '* mapcat (comment mapcat)}
                             #{(do) set contains? nil?}
                             #{, , , #_, , empty?}})
   false)

;; others
#(=
  (apply + (map count %))
  (count (apply clojure.set/union %)))

#(every? empty? (for [x % y % :when (not= x y)]
                  (clojure.set/intersection x y)))

(fn [set-of-sets]
  (apply distinct? (mapcat seq set-of-sets)))

(ns dojo.problems-in-4clojure.147-pascals-trapezoid)

(defn sol2 [v]
  (letfn [(append-zero [vv] (conj vv 0))
          (prepend-zero [vv] (into [0] vv))
          (combine [x y] (vec (map +' x y)))]
    (iterate #(combine (append-zero %) (prepend-zero %)) v)))

(def sol
  (fn [v]
    (iterate #(mapv +' (cons 0 %) (conj % 0)) v)))

(= (second (sol [2 3 2])) [2 5 5 2])

(= (take 5 (sol [1])) [[1] [1 1] [1 2 1] [1 3 3 1] [1 4 6 4 1]])

(= (take 2 (sol [3 1 2])) [[3 1 2] [3 4 3 2]])

(= (take 100 (sol [2 4 2])) (rest (take 101 (sol [2 2]))))


;; others

(fn [init]
  (iterate #(map +'
                 (concat [0] %)
                 (concat % [0]))
           init))

#(iterate (fn [xs] (map +' `(0 ~@xs) `(~@xs 0))) %)

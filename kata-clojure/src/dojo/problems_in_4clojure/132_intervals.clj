(ns dojo.problems-in-4clojure.132-intervals)

(defn sol [p v xs]
  )

(= '(1 :less 6 :less 7 4 3) (sol < :less [1 6 7 4 3]))
(= '(2) (sol > :more [2]))
(= [0 1 :x 2 :x 3 :x 4]  (sol #(and (pos? %) (< % %2)) :x (range 5)))
(empty? (sol > :more ()))
(= [0 1 :same 1 2 3 :same 5 8 13 :same 21]
   (take 12 (->> [0 1]
                 (iterate (fn [[a b]] [b (+ a b)]))
                 (map first) ; fibonacci numbers
                 (sol (fn [a b] ; both even or both odd
                       (= (mod a 2) (mod b 2)))
                     :same))))

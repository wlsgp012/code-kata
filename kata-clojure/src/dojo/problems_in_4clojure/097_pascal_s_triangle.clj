(ns dojo.problems-in-4clojure.097-pascal-s-triangle)
;; https://4clojure.oxal.org/#/problem/97

(defn sol [n]
  (if (= n 1)
    [1]
    (let [prev (sol (dec n))
          a (conj prev 0)
          b (into [0] prev)]
      (mapv +' a b))))


(= (sol 1) [1])

(= (map sol (range 1 6))
   [     [1]
    [1 1]
    [1 2 1]
    [1 3 3 1]
    [1 4 6 4 1]])

(= (sol 11)
   [1 10 45 120 210 252 210 120 45 10 1])


;; others
(defn pascals-triangle
  [n]
  (last (take n
              (iterate
               (fn [x] (mapv +' (cons 0 x) (conj x 0)))
               [1]))))

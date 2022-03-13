(ns dojo.problems-in-4clojure.079-triangle-minimal-path)
;; https://4clojure.oxal.org/#/problem/79

(defn sol
  [triangle]
  (apply min (map last (map-indexed (fn [idx x]
                                      (reduce (fn [[pos y], row] (let [get-neighbor #(nth row % Integer/MAX_VALUE)
                                                                       minimum (min (get-neighbor (dec pos)) (get-neighbor pos))]
                                                                   [(.indexOf row minimum) (+ y minimum)]))
                                              [idx x]
                                              (reverse (drop-last triangle))))
                                    (last triangle)))))


(= (sol [   [1]
         [2 4]
         [5 1 4]
         [2 3 4 5]])
   (+ 1 2 1 3)
   7)

(= (sol [     [3]
         [2 4]
         [1 9 3]
         [9 9 2 4]
         [4 6 6 7 8]
         [5 7 3 5 1 4]])
   (+ 3 4 3 2 7 1)
   20)

;; others
(fn [s]
  (last
   (reduce (fn [c l] (map #(+ (min %2 %3) %) l (rest c) c))
           (reverse s))))

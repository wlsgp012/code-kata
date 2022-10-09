(ns dojo.problems-in-4clojure.108-lazy-searching)

(defn sol [& vs]
  )

(= 3 (sol [3 4 5]))
(= 4 (sol [1 2 3 4 5 6 7] [0.5 3/2 4 19]))
(= 64 (sol (map #(* % % %) (range))
          (filter #(zero? (bit-and % (dec %))) (range))
          (iterate inc 20)))
(= 7 (sol (range) (range 0 100 7/6) [2 3 5 7 11 13]))

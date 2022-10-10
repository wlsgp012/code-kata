(ns dojo.problems-in-4clojure.108-lazy-searching)

(defn sol [& vs]
  (letfn [(subs [a b] (for [x a y b :when (= x y)] x))]
   (case (count vs)
     1 (apply min (first vs))
     2 (first (subs (first vs) (second vs)))
     (apply sol (conj (drop 2 vs) (subs (first vs) (second vs)))))))

(= 3 (sol [3 4 5]))
(= 4 (sol [1 2 3 4 5 6 7] [0.5 3/2 4 19]))
(comment (= 64 (sol (map #(* % % %) (range))
            (filter #(zero? (bit-and % (dec %))) (range))
            (iterate inc 20))))
(= 7 (sol (range) (range 0 100 7/6) [2 3 5 7 11 13]))

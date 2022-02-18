(ns dojo.problems-in-4clojure.060-sequence-reductions)
;; https://4clojure.oxal.org/#/problem/60

(defn sol
  ([f [x & xs]] (sol f x xs))
  ([f x ys]
   (lazy-seq (cons x (if (empty? ys)
                         nil
                         (sol f (f x (first ys)) (rest ys)))))))

(= (take 5 (sol + (range))) [0 1 3 6 10])
(= (sol conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]])
(= (last (sol * 2 [3 4 5])) (reduce * 2 [3 4 5]) 120)

;; others
(fn f
  ([g [h & r]] (f g h r))
  ([g i [h & r]]
   (cons i (if h (lazy-seq (f g (g i h) r))))))

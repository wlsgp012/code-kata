(ns dojo.problems-in-4clojure.062-reimplement-iteration)
;; https://4clojure.oxal.org/#/problem/62

(defn sol
  [f x]
  (lazy-seq (cons x (sol f (f x)))))

(= (take 5 (sol #(* 2 %) 1)) [1 2 4 8 16])
(= (take 100 (sol inc 0)) (take 100 (range)))
(= (take 9 (sol #(inc (mod % 3)) 1)) (take 9 (cycle [1 2 3])))

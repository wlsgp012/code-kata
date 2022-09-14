(ns dojo.problems-in-4clojure.166-comparisons)

(def sol
  (fn [cf x y]
    (case (cf x y)
      true :lt
      false (if (cf y x) :gt :eq))))

(= :gt (sol < 5 1))

(= :eq (sol (fn [x y] (< (count x) (count y))) "pear" "plum"))

(= :lt (sol (fn [x y] (< (mod x 5) (mod y 5))) 21 3))

(= :gt (sol > 0 2))

;; others
(fn comparison [operation a b]
  (cond
    (operation a b) :lt
    (operation b a) :gt
    :else :eq))

(fn [op a b]
  (case [(op a b) (op b a)]
    [true false] :lt
    [false false] :eq
    [false true] :gt))


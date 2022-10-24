(ns dojo.problems-in-4clojure.121-universal-computation-engine)

(defn sol [f]
  (fn [a]()))

(= 2 ((sol '(/ a b))
      '{b 8
        a 16}))
(= 8 ((sol '(+ a b 2))
      '{a 2
        b 4}))
(= [6 0 -4]
   (map (sol '(* (+ 2 a)
                (- 10 b)))
        '[{a 1
           b 8}
          {b 5
           a -2}
          {a 2
           b 11}]))
(= 1 ((sol '(/ (+ x 2)
              (* 3 (+ y 1))))
      '{x 4
        y 1}))

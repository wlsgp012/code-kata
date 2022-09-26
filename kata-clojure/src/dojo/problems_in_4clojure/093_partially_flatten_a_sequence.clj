(ns dojo.problems-in-4clojure.093-partially-flatten-a-sequence)

(def sol
  (fn [s]
    (letfn [(flat [ss] (mapcat #(if (sequential? %) (flat %) %) ss))]
      (mapcat flat s ))))

(= (sol [["Do"] ["Nothing"]])
   [["Do"] ["Nothing"]])

(= (sol [[[[:a :b]]] [[:c :d]] [:e :f]])
   [[:a :b] [:c :d] [:e :f]])

(= (sol '((1 2)((3 4)((((5 6)))))))
   '((1 2)(3 4)(5 6)))

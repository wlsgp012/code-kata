(ns dojo.problems-in-4clojure.081-set-intersection)
;; https://4clojure.oxal.org/#/problem/81

(defn sol
  [aset bset]
  (clojure.set/difference aset (clojure.set/difference aset bset)))


(= (sol #{0 1 2 3} #{2 3 4 5}) #{2 3})

(= (sol #{0 1 2} #{3 4 5}) #{})

(= (sol #{:a :b :c :d} #{:c :e :a :f :d}) #{:a :c :d})

;; others
(comp set filter)

#(set (for [x % y %2 :when (= x y)] x))

(comp set keep)

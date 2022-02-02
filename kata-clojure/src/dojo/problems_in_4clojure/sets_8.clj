(ns dojo.problems-in-4clojure.sets-8)
;; https://4clojure.oxal.org/#/problem/8

(def answer #{:a :b :c :d})

(= answer (set '(:a :a :b :c :c :c :c :d :d)))
(= answer (clojure.set/union #{:a :b :c} #{:b :c :d}))

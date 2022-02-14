(ns dojo.problems-in-4clojure.048-intro-to-some)
;; https://4clojure.oxal.org/#/problem/48

(def sol 6)

(= sol (some #{2 7 6} [5 6 7 8]))
(= sol (some #(when (even? %) %) [5 6 7 8]))

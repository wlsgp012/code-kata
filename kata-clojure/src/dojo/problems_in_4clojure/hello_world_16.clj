(ns dojo.problems-in-4clojure.hello-world-16)
;; https://4clojure.oxal.org/#/problem/16

(def answer #(str "Hello, " % "!"))

(= (answer "Dave") "Hello, Dave!")
(= (answer "Jenn") "Hello, Jenn!")
(= (answer "Rhea") "Hello, Rhea!")

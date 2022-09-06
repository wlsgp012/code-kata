(ns dojo.problems-in-4clojure.161-subset-and-superset)

(def sol #{1 2})

(clojure.set/superset? sol #{2})

(clojure.set/subset? #{1} sol)

(clojure.set/superset? sol #{1 2})

(clojure.set/subset? #{1 2} sol)

;; others
(set (take 3 (range)))

(into #{} (range 3))

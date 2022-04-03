(ns dojo.design-pattern.023-bridge)

;; abstraction
(derive ::clojure-job ::job)
(derive ::java-job ::job)
(derive ::senior-clojure-job ::clojure-job)
(derive ::senior-java-job ::java-job)

;; implementation
(defmulti accept :job)

(defmethod accept :java [candidate]
  (and (some #{:java} (:skills candidate))
       (> (:exprience candidate) 1)))

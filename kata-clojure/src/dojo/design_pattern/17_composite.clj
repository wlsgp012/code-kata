(ns dojo.design-pattern.17-composite)

(def tree
  '(:a
    (:b
     (:e (:f)
         (:g)))
    (:c
     (:h))
    (:d
     (:j)
     (:k
      (:l)
      (:m)
      (:n)))))

(map first
     (tree-seq next rest tree))

// Exercise 15.1-5
// Fibonacci using dynamic programming
const fib = num => {
  if (num === 1) return 1
  let a = 1, b = 1, tmp = 0
  for (let i = 0; i < num - 2; i++) {
    tmp = a + b
    a = b
    b = tmp
  }
  return b
}

console.log(fib(5))


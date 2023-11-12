export function useFetch(url, method = "GET", body) {
  const options = {
    method,
    headers: { "Content-Type": "application/json" },
  };

  if (body) {
    options["body"] = JSON.stringify(body);
  }

  return fetch(url, options);
}

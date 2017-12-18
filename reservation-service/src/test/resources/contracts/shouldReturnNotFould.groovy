import org.springframework.cloud.contract.spec.Contract

Contract.make {
  description "should return with Not Found"

  request {
    url "/reservations/-1"
    method GET()
  }

  response {
    status 404
  }
}

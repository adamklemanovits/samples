import org.springframework.cloud.contract.spec.Contract

Contract.make {
  description "should return with Bad Request if request body is null"

  request {
    url "/reservations"
    method POST()
    setBody(null)
  }

  response {
    status 400
  }
}

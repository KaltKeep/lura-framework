import { http } from "@/utils/http";

export type GatewayLog = {
  id: number;
  targetServer: string;
  requestPath: string;
  requestMethod: string;
  url: string;
  requestBody: string;
  responseData: string;
  ip: string;
  requestTime: string;
  responseTime: string;
  executeTime: number;
};

export type GatewayPredicate = {
  name: string;
  args: {
    additionalProp1: string;
    additionalProp2: string;
    additionalProp3: string;
  };
};
export type GatewayFilter = {
  name: string;
  args: {
    additionalProp1: string;
    additionalProp2: string;
    additionalProp3: string;
  };
};

export type GatewayRoute = {
  /** ID */
  id: string;
  predicates: Array<GatewayPredicate>;
  filters: Array<GatewayFilter>;
  uri: string;
  metadata: {
    additionalProp1: {};
    additionalProp2: {};
    additionalProp3: {};
  };
  order: number;
};

export const getGateWayLogs = () => {
  return http.request<GatewayLog[]>("get", "/api/gateway/logs");
};
export const getGateWayRoutes = () => {
  return http.request<GatewayRoute[]>("get", "/api/gateway/routes");
};
export const addGateWayRoutes = () => {
  return http.request<GatewayRoute[]>("post", "/api/gateway/routes");
};
